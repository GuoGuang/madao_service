package com.madao.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madao.api.BaseServiceRpc;
import com.madao.constant.CommonConst;
import com.madao.event.LoginSuccessEvent;
import com.madao.model.dto.user.AuthToken;
import com.madao.model.entity.base.LoginLog;
import com.madao.utils.HttpServletUtil;
import com.madao.utils.JsonData;
import com.madao.utils.JsonUtil;
import com.madao.utils.RedisUtil;
import com.madao.utils.security.JWTAuthentication;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 认证成功处理器
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@AllArgsConstructor
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;
    private final BaseServiceRpc baseServiceRpc;
    private final RedisUtil redisUtil;
    private final RegisteredClientRepository registeredClientRepository;
    private final ApplicationContext applicationContext;
    private final OAuth2AuthorizedClientManager authorizedClientManager;

    /**
     * 处理用户登录成功后操作；
     * 返回JWT，写入redis
     * TODO MQ操作
     * - 修改上次登录时间
     * - 写入登录日志
     **/
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("登录成功");

        String header = request.getHeader("Authorization");
        String[] tokens = extractAndDecodeHeader(header);
        assert tokens.length == 2;
        String clientId = tokens[0];

        RegisteredClient client = registeredClientRepository.findByClientId(clientId);
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId(client.getClientId()).principal("XcWebApp").build();
        OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(authorizeRequest);
        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        AuthToken authToken = new AuthToken();
        authToken.setAccess_token(accessToken);
        authToken.setRefresh_token(authorizedClient.getRefreshToken().getTokenValue());

        // 插入登录日志
        Map<String, String> stringStringMap = JWTAuthentication.parseJwtToClaims(accessToken);
        insertLoginLog(accessToken, stringStringMap.get("id"), request);

        // 更新用户相关信息：更新last_date字段
        // rabbitUtil.sendMessageToExchange();

        String jsonString = JsonUtil.toJsonString(authToken);
        saveToken(accessToken, jsonString, CommonConst.TIME_OUT_DAY);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(JsonData.success(accessToken)));

        // 监听者模式处理登录成功事件
        applicationContext.publishEvent(new LoginSuccessEvent(this, authToken));

    }

    /**
     * 添加登录日志
     *
     * @param auth    令牌
     * @param userId  用户id
     * @param request request
     */
    private void insertLoginLog(String auth, String userId, HttpServletRequest request) {
        // 添加登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setClientIp(HttpServletUtil.getIpAddr(request));
        loginLog.setUserId(userId);
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        loginLog.setBrowser(userAgent.getBrowser().getName());
        loginLog.setOsInfo(userAgent.getOperatingSystem().getName());
        baseServiceRpc.insertLoginLog(loginLog);
    }

    /**
     * 提取并解码头数据
     */
    private static String[] extractAndDecodeHeader(String header) {
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decoded;
        try {
            decoded = java.util.Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }
        String token = new String(decoded, StandardCharsets.UTF_8);
        int delim = token.indexOf(':');
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }

    /**
     * @param accessToken 用户身份令牌
     * @param content     内容就是AuthToken对象的内容
     * @param ttl         过期时间
     */
    private boolean saveToken(String accessToken, String content, long ttl) {
        String key = "user_token:" + accessToken;
        redisUtil.setKeyStr(key, content, ttl);
        Long expire = redisUtil.getExpire(key);
        return expire > 0;
    }


}

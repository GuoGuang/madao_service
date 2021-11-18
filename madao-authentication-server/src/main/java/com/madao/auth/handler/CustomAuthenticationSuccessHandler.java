package com.madao.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madao.api.BaseServiceRpc;
import com.madao.constant.CommonConst;
import com.madao.model.dto.user.AuthToken;
import com.madao.model.entity.base.LoginLog;
import com.madao.redis.RedisService;
import com.madao.utils.HttpServletUtil;
import com.madao.utils.JsonData;
import com.madao.utils.JsonUtil;
import com.madao.utils.security.JWTAuthentication;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证成功处理器
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    BaseServiceRpc baseServiceRpc;

//	@Autowired
//	RabbitUtil rabbitUtil;


    /**
     * 处理用户登录成功后操作；
     * 返回JWT，写入redis
     * TODO MQ操作
     * - 修改上次登录时间
     * - 写入登录日志
     *
     * @param request
     * @param response
     * @param authentication
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("登录成功");

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Basic ")) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }

        String[] tokens = extractAndDecodeHeader(header);
        assert tokens.length == 2;

        String clientId = tokens[0];
//		String clientSecret = tokens[1];

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

		/*if (clientDetails == null) {
			throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
		} else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
			throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
		}*/

        TokenRequest tokenRequest = new TokenRequest(new HashMap<>(), clientId, clientDetails.getScope(), "custom");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

        Map<String, Object> custInformation = token.getAdditionalInformation();
        Object jti = custInformation.get("jti");
        String accessToken = token.getValue();
        OAuth2RefreshToken refreshToken = token.getRefreshToken();

        AuthToken authToken = new AuthToken();
        authToken.setAccess_token(accessToken);
        authToken.setRefresh_token(refreshToken.getValue());
        authToken.setJwt_token(jti.toString());

        // 插入登录日志
        Map<String, String> stringStringMap = JWTAuthentication.parseJwtToClaims(accessToken);
        insertLoginLog(accessToken, stringStringMap.get("id"), request);

        // 更新用户相关信息：更新last_date字段
        // rabbitUtil.sendMessageToExchange();

        String jsonString = JsonUtil.toJsonString(authToken);
        saveToken(jti.toString(), jsonString, CommonConst.TIME_OUT_DAY);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(JsonData.success(accessToken)));

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
     * @param accessToken 用户身份令牌
     * @param content     内容就是AuthToken对象的内容
     * @param ttl         过期时间
     */
    private boolean saveToken(String accessToken, String content, long ttl) {
        String key = "user_token:" + accessToken;
        redisService.setKeyStr(key, content, ttl);
        Long expire = redisService.getExpire(key);
        return expire > 0;
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

}

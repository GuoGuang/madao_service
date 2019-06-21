package com.youyd.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youyd.api.base.LoginLogServiceRpc;
import com.youyd.cache.redis.RedisService;
import com.youyd.constant.CommonConst;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.base.LoginLog;
import com.youyd.pojo.user.AuthToken;
import com.youyd.utils.DateUtil;
import com.youyd.utils.HttpServletUtil;
import com.youyd.utils.JsonData;
import com.youyd.utils.JsonUtil;
import com.youyd.utils.security.JWTAuthentication;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
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
import java.util.HashMap;
import java.util.Map;

/**
 * APP环境下认证成功处理器
 * 
 * @author zhailiang
 *
 */
@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

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
	LoginLogServiceRpc loginLogServiceRpc;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException, ServletException {

		logger.info("登录成功");

		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Basic ")) {
			throw new UnapprovedClientAuthenticationException("请求头中无client信息");
		}

		String[] tokens = extractAndDecodeHeader(header, request);
		assert tokens.length == 2;

		String clientId = tokens[0];
		String clientSecret = tokens[1];

		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

		if (clientDetails == null) {
			throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
		} else if (!StringUtils.equals(clientDetails.getClientSecret(), passwordEncoder.encode(clientSecret))) {
			throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
		}

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
		insertLoginLog(jti.toString(),stringStringMap.get("id"),request);


		String jsonString = JsonUtil.toJsonString(authToken);
		JsonData jsonData = new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), jti);
		saveToken(jti.toString(), jsonString, CommonConst.TIME_OUT_DAY);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(jsonData));


	}

	/**
	 * 添加登录日志
	 * @param auth 令牌
	 * @param userId 用户id
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
		loginLog.setCreateAt(DateUtil.getTimestamp());
		loginLog.setUpdateAt(DateUtil.getTimestamp());
		loginLogServiceRpc.insertLoginLog("Bearer "+auth,loginLog);

	}

	/**
	 *
	 * @param access_token 用户身份令牌
	 * @param content  内容就是AuthToken对象的内容
	 * @param ttl 过期时间
	 * @return
	 */
	private boolean saveToken(String access_token,String content,long ttl){
		String key = "user_token:" + access_token;
		redisService.setKeyStr(key,content,ttl);
		Long expire = redisService.getExpire(key);
		return expire>0;
	}

	private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {

		byte[] base64Token = header.substring(6).getBytes("UTF-8");
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		} catch (IllegalArgumentException e) {
			throw new BadCredentialsException("Failed to decode basic authentication token");
		}

		String token = new String(decoded, "UTF-8");

		int delim = token.indexOf(":");

		if (delim == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		}
		return new String[] { token.substring(0, delim), token.substring(delim + 1) };
	}

}

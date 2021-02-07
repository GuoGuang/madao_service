package com.madao.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madao.api.base.LoginLogServiceRpc;
import com.madao.constant.CommonConst;
import com.madao.db.redis.service.RedisService;
import com.madao.model.dto.user.AuthToken;
import com.madao.utils.JsonData;
import com.madao.utils.JsonUtil;
import com.madao.utils.LogBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 三方登录（Github、QQ、Wechat）成功处理器
 **/
@Component
public class OauthLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private RedisService redisService;

	@Autowired
	private ClientDetailsService clientDetailsService;

	@Autowired
	private AuthorizationServerTokenServices authorizationServerTokenServices;

	@Autowired
	private OAuth2ClientProperties oAuth2ClientProperties;

	@Autowired
	LoginLogServiceRpc loginLogServiceRpc;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		LogBack.info("三方登录成功！");
		OAuth2ClientProperties.Registration github = oAuth2ClientProperties.getRegistration().get("github");

		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(github.getClientId());

		TokenRequest tokenRequest = new TokenRequest(new HashMap<>(), github.getClientId(), clientDetails.getScope(), "custom");
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

		String jsonString = JsonUtil.toJsonString(authToken);
		saveToken(jti.toString(), jsonString, CommonConst.TIME_OUT_DAY);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().write(objectMapper.writeValueAsString(JsonData.success(jti)));
	}

	private boolean saveToken(String accessToken, String content, long ttl) {
		String key = "user_token:" + accessToken;
		redisService.setKeyStr(key, content, ttl);
		Long expire = redisService.getExpire(key);
		return expire > 0;
	}

}


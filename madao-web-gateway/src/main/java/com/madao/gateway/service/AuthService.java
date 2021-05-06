package com.madao.gateway.service;

import com.madao.api.auth.AuthServiceRpc;
import com.madao.exception.custom.RemoteRpcException;
import com.madao.utils.JsonData;
import com.madao.utils.security.JWTAuthentication;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class AuthService {

	@Autowired
	private AuthServiceRpc authServiceRpc;

	/**
	 * 不需要网关签权的url配置(/oauth,/open)
	 * 默认/oauth开头是不需要的
	 */
	@Value("${auth.ignoreUrls}")
	private String[] ignoreUrls;


	@Value("${auth.commonUrls}")
	private String[] commonUrls;

	/**
	 * jwt验签
	 * private MacSigner verifier;
	 */

	public JsonData<Object> authenticate(String url, String method, String authentication) {
		JsonData<Object> jsonData = authServiceRpc.authPermission(url, method, authentication);
		if (!JsonData.isSuccess(jsonData)) {
			throw new RemoteRpcException(jsonData);
		}
		return jsonData;
	}

	public boolean ignoreAuthentication(String url) {
		return Stream.of(ignoreUrls).anyMatch(
				ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)));
	}

	public boolean commonAuthentication(String url) {
		return Stream.of(commonUrls).anyMatch(
				ignoreUrl -> url.startsWith(StringUtils.trim(ignoreUrl)));
	}

	public boolean hasPermission(JsonData<Object> authJson) {
		return authJson.isStatus();
	}

	public boolean hasPermission(String authentication, String url, String method) {
		//token是否有效
		if (JWTAuthentication.invalidJwtAccessToken(authentication)) {
			return Boolean.FALSE;
		}
		//从认证服务获取是否有权限
		return hasPermission(authenticate(url, method, authentication));
	}

}

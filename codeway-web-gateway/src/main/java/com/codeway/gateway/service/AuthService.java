package com.codeway.gateway.service;


import com.codeway.api.auth.AuthServiceRpc;
import com.codeway.db.redis.service.RedisService;
import com.codeway.exception.custom.CaptchaNotMatchException;
import com.codeway.exception.custom.RemoteRpcException;
import com.codeway.model.dto.user.AuthToken;
import com.codeway.utils.JsonData;
import com.codeway.utils.JsonUtil;
import com.codeway.utils.security.JWTAuthentication;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class AuthService {

	@Autowired
	private AuthServiceRpc authServiceRpc;

	@Autowired
	private RedisService redisService;

	private static final String BEARER = "Bearer ";

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
    private MacSigner verifier;
	 */

    public JsonData<Object> authenticate(String authentication, String url, String method) {
	    JsonData<Object> jsonData = authServiceRpc.authPermission(url,method,BEARER+authentication);
	    if (!JsonData.isSuccess(jsonData)){
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
        return hasPermission(authenticate(authentication, url, method));
    }



    public Jwt getJwt(String authentication) {
        return JwtHelper.decode(authentication);
    }

	/**
	 * 获取令牌有效期
	 * @param authentication
	 * @return
	 */
	public long getExpire(String authentication){
		//key
		String key = "user_token:"+JWTAuthentication.getFullAuthorization(authentication);
		Long expire = redisService.getExpire(key);
		return expire;
	}
	/**
	 * 根据jti获取access_token
	 * @param authentication
	 * @return
	 */
	public AuthToken getAuthToken(String authentication) {
		//key
		String key = "user_token:" + JWTAuthentication.getFullAuthorization(authentication);
		String expire = redisService.getKeyStr(key)
				.orElseThrow(CaptchaNotMatchException::new)
				.toString();
		AuthToken authToken = JsonUtil.jsonToPojo(expire, AuthToken.class);
		return authToken;
	}
}

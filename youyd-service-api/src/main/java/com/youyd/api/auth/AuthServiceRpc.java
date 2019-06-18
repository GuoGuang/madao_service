package com.youyd.api.auth;

import com.youyd.constant.FeignConst;
import com.youyd.fallback.auth.AuthServiceRpcFallbackFactory;
import com.youyd.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 鉴权服务api
 * @author : LGG
 * @create : 2019-06-12
 **/

@FeignClient(value = FeignConst.SERVICE_AUTHORIZATION_AUTH,
			 path = FeignConst.SERVICE_AUTHORIZATION_AUTH_PATH,
			 fallbackFactory = AuthServiceRpcFallbackFactory.class)
public interface AuthServiceRpc {

	/**
	 * 调用签权服务，判断用户是否有权限
	 */
	@PostMapping(value = "auth/permission")
	JsonData authPermission(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication);
}
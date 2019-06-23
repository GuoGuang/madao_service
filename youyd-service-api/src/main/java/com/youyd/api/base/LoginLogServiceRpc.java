package com.youyd.api.base;

import com.youyd.constant.FeignConst;
import com.youyd.fallback.base.LoginLogServiceRpcFallbackFactory;
import com.youyd.pojo.base.LoginLog;
import com.youyd.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 登录日志api
 * @author : LGG
 * @create : 2018-09-27
 **/

@FeignClient(value = FeignConst.SERVICE_BASE,path = FeignConst.SERVICE_BASE_PATH,fallbackFactory = LoginLogServiceRpcFallbackFactory.class)
public interface LoginLogServiceRpc {

	/**
	 * 增加登录日志
	 * @param loginLog 登录日志实体
	 * @return JsonData
	 */
	@PostMapping
	JsonData insertLoginLog(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody LoginLog loginLog);
}

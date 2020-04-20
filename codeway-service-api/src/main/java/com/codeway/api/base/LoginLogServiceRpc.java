package com.codeway.api.base;

import com.codeway.constant.FeignConst;
import com.codeway.fallback.base.LoginLogServiceRpcFallbackFactory;
import com.codeway.pojo.base.LoginLog;
import com.codeway.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 登录日志api
 **/

@FeignClient(contextId = "loginLogClient",
		value = FeignConst.SERVICE_BASE,
		path = FeignConst.SERVICE_BASE_LOGIN_LOG_PATH,
		fallbackFactory = LoginLogServiceRpcFallbackFactory.class)
public interface LoginLogServiceRpc {

	/**
     * 增加登录日志
     *
     * @param loginLog 登录日志实体
     * @return JsonData
     */
    @PostMapping
    JsonData<Void> insertLoginLog(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @RequestBody LoginLog loginLog);
}

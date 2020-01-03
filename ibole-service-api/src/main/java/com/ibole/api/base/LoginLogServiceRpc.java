package com.ibole.api.base;

import com.ibole.constant.FeignConst;
import com.ibole.fallback.base.LoginLogServiceRpcFallbackFactory;
import com.ibole.pojo.base.LoginLog;
import com.ibole.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 登录日志api
 **/

@FeignClient(contextId = "loginLogClient", value = FeignConst.SERVICE_BASE, path = FeignConst.SERVICE_BASE_PATH, fallbackFactory = LoginLogServiceRpcFallbackFactory.class)
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

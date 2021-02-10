package com.madao.api.base;

import com.madao.constant.FeignConst;
import com.madao.fallback.base.LoginLogServiceRpcFallbackFactory;
import com.madao.model.pojo.base.LoginLog;
import com.madao.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    JsonData<Void> insertLoginLog(@RequestBody LoginLog loginLog);
}

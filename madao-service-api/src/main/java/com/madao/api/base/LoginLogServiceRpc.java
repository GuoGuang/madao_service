package com.madao.api.base;

import com.madao.constant.FeignConst;
import com.madao.fallback.base.LoginLogServiceRpcFallbackFactory;
import com.madao.model.entity.base.LoginLog;
import com.madao.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 登录日志api
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
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

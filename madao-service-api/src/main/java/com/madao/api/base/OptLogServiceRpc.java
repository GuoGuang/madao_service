package com.madao.api.base;

import com.madao.constant.FeignConst;
import com.madao.fallback.base.OptLogServiceRpcFallbackFactory;
import com.madao.model.entity.base.OptLog;
import com.madao.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 操作日志api
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@FeignClient(contextId = "optLogClient",
        value = FeignConst.SERVICE_BASE,
        path = FeignConst.SERVICE_BASE_OPT_LOG_PATH,
        fallbackFactory = OptLogServiceRpcFallbackFactory.class)
public interface OptLogServiceRpc {

    /**
     * 增加操作日志
     *
     * @param optLog 操作日志实体
     * @return JsonData
     */
    @PostMapping
    JsonData<Object> insertOptLog(@RequestBody OptLog optLog);
}

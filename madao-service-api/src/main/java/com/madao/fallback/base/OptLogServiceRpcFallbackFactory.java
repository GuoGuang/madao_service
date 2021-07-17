package com.madao.fallback.base;

import com.madao.api.base.OptLogServiceRpc;
import com.madao.enums.StatusEnum;
import com.madao.utils.JsonData;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Component
public class OptLogServiceRpcFallbackFactory implements FallbackFactory<OptLogServiceRpc> {

    private static final String ERROR_INFO = "接口OptLogServiceRpc.{}远程调用失败，该服务已经停止或者不可访问,参数为:{}";

    @Override
    public OptLogServiceRpc create(Throwable throwable) {
        return optLog -> {
            log.error(ERROR_INFO, "insertOptLog", optLog, throwable);
            return JsonData.failed(StatusEnum.RPC_ERROR);
        };
    }
}

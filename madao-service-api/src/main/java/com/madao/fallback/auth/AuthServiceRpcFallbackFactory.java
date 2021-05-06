package com.madao.fallback.auth;

import com.madao.api.auth.AuthServiceRpc;
import com.madao.enums.StatusEnum;
import com.madao.utils.JsonData;
import com.madao.utils.LogBack;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


/**
 * 接口调用失败处理
 **/

@Component
public class AuthServiceRpcFallbackFactory implements FallbackFactory<AuthServiceRpc> {

    private static final String ERROR_INFO = "接口AuthServiceRpc.{}远程调用失败，该服务已经停止或者不可访问,参数为:{}";

    @Override
    public AuthServiceRpc create(Throwable throwable) {
        return (url, method, authentication) -> {
            LogBack.error(ERROR_INFO, "login", authentication, throwable);
            return JsonData.failed(StatusEnum.RPC_ERROR);
        };
    }
}

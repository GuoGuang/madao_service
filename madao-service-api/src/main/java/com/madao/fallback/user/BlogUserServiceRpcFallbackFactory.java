package com.madao.fallback.user;

import com.madao.api.user.BlogUserServiceRpc;
import com.madao.enums.StatusEnum;
import com.madao.utils.JsonData;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Component
public class BlogUserServiceRpcFallbackFactory implements FallbackFactory<BlogUserServiceRpc> {

    private static final String ERROR_INFO = "接口UserServiceRpc.{}远程调用失败，该服务已经停止或者不可访问,参数为:{}";

    @Override
    public BlogUserServiceRpc create(Throwable throwable) {
        return new BlogUserServiceRpc() {
            @Override
            public JsonData loginWithGithub(Map<String, Object> userInfo) {
                log.error(ERROR_INFO, "registerUserWithGithub", userInfo, throwable);
                return JsonData.failed(StatusEnum.RPC_ERROR);
            }
        };
    }
}

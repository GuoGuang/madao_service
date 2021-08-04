package com.madao.fallback.user;

import com.madao.api.user.ResourceServiceRpc;
import com.madao.enums.StatusEnum;
import com.madao.model.entity.user.Resource;
import com.madao.utils.JsonData;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Component
public class ResourceServiceRpcFallbackFactory implements FallbackFactory<ResourceServiceRpc> {

    private static final String ERROR_INFO = "ResourceServiceRpc.{}远程调用失败，该服务已经停止或者不可访问,参数为:{}";

    @Override
    public ResourceServiceRpc create(Throwable throwable) {
        return new ResourceServiceRpc() {

            @Override
            public JsonData<List<Resource>> findResourceByCondition() {
                log.error(ERROR_INFO, "findResourceByCondition", throwable);
                return JsonData.failed(StatusEnum.RPC_ERROR);

            }

            @Override
            public JsonData<List<Resource>> findResourceByRoleIds(String[] roleIds) {
                log.error(ERROR_INFO, "findResourceByCondition", roleIds, throwable);
                return JsonData.failed(StatusEnum.RPC_ERROR);

            }
        };
    }
}

package com.madao.fallback.user;

import com.madao.api.user.UserServiceRpc;
import com.madao.enums.StatusEnum;
import com.madao.model.dto.user.UserDto;
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
public class UserServiceRpcFallbackFactory implements FallbackFactory<UserServiceRpc> {

    private static final String ERROR_INFO = "接口UserServiceRpc.{}远程调用失败，该服务已经停止或者不可访问,参数为:{}";

    @Override
    public UserServiceRpc create(Throwable throwable) {
        return new UserServiceRpc() {
            @Override
            public JsonData<UserDto> getUserInfo(String account) {
                log.error(ERROR_INFO, "getUserInfo", account, throwable);
                return JsonData.failed(StatusEnum.RPC_ERROR);
            }

            @Override
            public JsonData<UserDto> getUserInfoById(String userId) {
                log.error(ERROR_INFO, "getUserInfoById", userId, throwable);
                return JsonData.failed(StatusEnum.RPC_ERROR);
            }

            @Override
            public JsonData<List<UserDto>> getUserInfoByIds(String[] articleIds) {
                log.error(ERROR_INFO, "getUserInfoById", articleIds, throwable);
                return JsonData.failed(StatusEnum.RPC_ERROR);
            }
        };
    }
}

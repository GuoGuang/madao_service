package com.ibole.fallback.user;

import com.ibole.api.user.UserServiceRpc;
import com.ibole.config.CustomQueryResults;
import com.ibole.enums.StatusEnum;
import com.ibole.pojo.user.User;
import com.ibole.utils.JsonData;
import com.ibole.utils.LogBack;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


/**
 * 接口调用失败处理
 **/

@Component
public class UserServiceRpcFallbackFactory implements FallbackFactory<UserServiceRpc> {

	private static final String ERROR_INFO = "接口UserServiceRpc.{}远程调用失败，该服务已经停止或者不可访问,参数为:{}";

	@Override
	public UserServiceRpc create(Throwable throwable) {
		return new UserServiceRpc() {
            @Override
            public JsonData<CustomQueryResults<User>> findUserByUser(User user) {
                LogBack.error(ERROR_INFO, "findUserByUser", user, throwable);
                return JsonData.failed(StatusEnum.RPC_ERROR);
            }
            @Override
            public JsonData<User> findUserByAccount(User account) {
                LogBack.error(ERROR_INFO, "findUserByAccount", account, throwable);
                return JsonData.failed(StatusEnum.RPC_ERROR);
            }
            @Override
            public JsonData<CustomQueryResults<User>> findUser() {
                return JsonData.failed(StatusEnum.RPC_ERROR);
            }
        };
	}
}

package com.codeway.fallback.user;

import com.codeway.api.user.UserServiceRpc;
import com.codeway.enums.StatusEnum;
import com.codeway.utils.JsonData;
import com.codeway.utils.LogBack;
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
		return user -> {
			LogBack.error(ERROR_INFO, "getUserInfo", user, throwable);
			return JsonData.failed(StatusEnum.RPC_ERROR);
		};
	}
}

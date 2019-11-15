package com.ibole.fallback.user;

import com.ibole.api.user.UserServiceRpc;
import com.ibole.enums.StatusEnum;
import com.ibole.pojo.user.User;
import com.ibole.utils.JsonData;
import com.ibole.utils.LogBack;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * 接口调用失败处理
 * @author : LGG
 * @create : 2018-09-27
 **/

@Component
public class UserServiceRpcFallbackFactory implements FallbackFactory<UserServiceRpc> {

	private static final String ERROR_INFO = "接口UserServiceRpc.{}远程调用失败，该服务已经停止或者不可访问,参数为:{}";

	@Override
	public UserServiceRpc create(Throwable throwable) {
		return new UserServiceRpc() {

			@Override
			public JsonData login(HttpServletRequest request) {
				LogBack.error(ERROR_INFO,"login",request,throwable);
				return new JsonData(false, StatusEnum.RPC_ERROR.getCode(), StatusEnum.RPC_ERROR.getMsg());
			}

			@Override
			public JsonData findUserByUser(User user) {
				LogBack.error(ERROR_INFO,"findUserByUser",user,throwable);
				return new JsonData<User>(false, StatusEnum.RPC_ERROR.getCode(), StatusEnum.RPC_ERROR.getMsg());
			}

			@Override
			public JsonData findUser() {
				return new JsonData<User>(false, StatusEnum.RPC_ERROR.getCode(), StatusEnum.RPC_ERROR.getMsg());
			}
		};
	}
}

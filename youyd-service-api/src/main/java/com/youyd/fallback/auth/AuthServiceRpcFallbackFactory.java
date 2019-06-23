package com.youyd.fallback.auth;

import com.youyd.api.auth.AuthServiceRpc;
import com.youyd.enums.StatusEnum;
import com.youyd.utils.JsonData;
import com.youyd.utils.LogBack;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


/**
 * 接口调用失败处理
 *
 * @author : LGG
 * @create : 2019-06-12
 **/

@Component
public class AuthServiceRpcFallbackFactory implements FallbackFactory<AuthServiceRpc> {

	private static final String ERROR_INFO = "接口AuthServiceRpc.{}远程调用失败，该服务已经停止或者不可访问,参数为:{}";

	@Override
	public AuthServiceRpc create(Throwable throwable) {
		return new AuthServiceRpc() {

			@Override
			public JsonData authPermission(String url,String method,String authentication) {
				LogBack.error(ERROR_INFO, "login", authentication, throwable);
				return new JsonData(false, StatusEnum.RPC_ERROR.getCode(), StatusEnum.RPC_ERROR.getMsg());
			}
		};
	}
}

package com.ibole.fallback.base;

import com.ibole.api.base.LoginLogServiceRpc;
import com.ibole.enums.StatusEnum;
import com.ibole.pojo.base.LoginLog;
import com.ibole.utils.JsonData;
import com.ibole.utils.LogBack;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


/**
 * 接口调用失败处理
 **/

@Component
public class LoginLogServiceRpcFallbackFactory implements FallbackFactory<LoginLogServiceRpc> {

	private static final String ERROR_INFO = "接口LoginLogServiceRpc.{}远程调用失败，该服务已经停止或者不可访问,参数为:{}";

	@Override
	public LoginLogServiceRpc create(Throwable throwable) {
		return new LoginLogServiceRpc() {

			@Override
			public JsonData insertLoginLog(String auth,LoginLog loginLog) {
				LogBack.error(ERROR_INFO,"insertLoginLog",loginLog,throwable);
				return new JsonData(false, StatusEnum.RPC_ERROR.getCode(), StatusEnum.RPC_ERROR.getMsg());
			}
		};
	}
}

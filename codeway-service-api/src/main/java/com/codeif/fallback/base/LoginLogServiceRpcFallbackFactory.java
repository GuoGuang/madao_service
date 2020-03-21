package com.codeif.fallback.base;

import com.codeif.api.base.LoginLogServiceRpc;
import com.codeif.enums.StatusEnum;
import com.codeif.pojo.base.LoginLog;
import com.codeif.utils.JsonData;
import com.codeif.utils.LogBack;
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
			public JsonData<Void> insertLoginLog(String auth, LoginLog loginLog) {
				LogBack.error(ERROR_INFO, "insertLoginLog", loginLog, throwable);
				return JsonData.failed(StatusEnum.RPC_ERROR);
			}
		};
	}
}

package com.ibole.fallback.base;

import com.ibole.api.base.OptLogServiceRpc;
import com.ibole.enums.StatusEnum;
import com.ibole.pojo.base.OptLog;
import com.ibole.utils.JsonData;
import com.ibole.utils.LogBack;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


/**
 * 接口调用失败处理
 * @author : LGG
 * @create : 2018-09-27
 **/

@Component
public class OptLogServiceRpcFallbackFactory implements FallbackFactory<OptLogServiceRpc> {

	private static final String ERROR_INFO = "接口OptLogServiceRpc.{}远程调用失败，该服务已经停止或者不可访问,参数为:{}";

	@Override
	public OptLogServiceRpc create(Throwable throwable) {
		return new OptLogServiceRpc() {

			@Override
			public JsonData insertOptLog(OptLog optLog) {
				LogBack.error(ERROR_INFO,"insertOptLog",optLog,throwable);
				return new JsonData(false, StatusEnum.RPC_ERROR.getCode(), StatusEnum.RPC_ERROR.getMsg());
			}
		};
	}
}

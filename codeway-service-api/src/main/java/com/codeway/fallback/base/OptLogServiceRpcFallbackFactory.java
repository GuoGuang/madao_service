package com.madaoo.fallback.base;

import com.madaoo.api.base.OptLogServiceRpc;
import com.madaoo.enums.StatusEnum;
import com.madaoo.utils.JsonData;
import com.madaoo.utils.LogBack;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


/**
 * 接口调用失败处理
 **/

@Component
public class OptLogServiceRpcFallbackFactory implements FallbackFactory<OptLogServiceRpc> {

	private static final String ERROR_INFO = "接口OptLogServiceRpc.{}远程调用失败，该服务已经停止或者不可访问,参数为:{}";

	@Override
	public OptLogServiceRpc create(Throwable throwable) {
		return optLog -> {
LogBack.error(ERROR_INFO, "insertOptLog", optLog, throwable);
return JsonData.failed(StatusEnum.RPC_ERROR);
};
	}
}

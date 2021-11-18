package com.madao.fallback;

import com.madao.api.BaseServiceRpc;
import com.madao.enums.StatusEnum;
import com.madao.model.entity.base.LoginLog;
import com.madao.model.entity.base.OptLog;
import com.madao.utils.JsonData;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Component
public class BaseServiceRpcFallbackFactory implements FallbackFactory<BaseServiceRpc> {

	private static final String ERROR_INFO = "接口BaseServiceRpc.{}远程调用失败，该服务已经停止或者不可访问,参数为:{}";

	@Override
	public BaseServiceRpc create(Throwable throwable) {
		return new BaseServiceRpc() {
			@Override
			public JsonData<Object> insertOptLog(OptLog optLog) {
				log.error(ERROR_INFO, "insertOptLog", optLog, throwable);
				return JsonData.failed(StatusEnum.RPC_ERROR);
			}

			@Override
			public JsonData<Void> insertLoginLog(LoginLog loginLog) {
				log.error(ERROR_INFO, "insertLoginLog", loginLog, throwable);
				return JsonData.failed(StatusEnum.RPC_ERROR);
			}
		};
	}
}

package com.madaoo.api.base;

import com.madaoo.constant.FeignConst;
import com.madaoo.fallback.base.OptLogServiceRpcFallbackFactory;
import com.madaoo.model.pojo.base.OptLog;
import com.madaoo.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 操作日志api
 **/

@FeignClient(contextId = "optLogClient",
		value = FeignConst.SERVICE_BASE,
		path = FeignConst.SERVICE_BASE_OPT_LOG_PATH,
		fallbackFactory = OptLogServiceRpcFallbackFactory.class)
public interface OptLogServiceRpc {

	/**
	 * 增加操作日志
	 * @param optLog 操作日志实体
	 * @return JsonData
	 */
	@PostMapping
	JsonData<Object> insertOptLog(@RequestBody OptLog optLog);
}

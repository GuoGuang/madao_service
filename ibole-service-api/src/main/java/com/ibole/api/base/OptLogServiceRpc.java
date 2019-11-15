package com.ibole.api.base;

import com.ibole.constant.FeignConst;
import com.ibole.fallback.base.OptLogServiceRpcFallbackFactory;
import com.ibole.pojo.base.OptLog;
import com.ibole.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 操作日志api
 * @author : LGG
 * @create : 2018-09-27
 **/

@FeignClient(value = FeignConst.SERVICE_BASE,path = "/optLog",
		fallbackFactory = OptLogServiceRpcFallbackFactory.class)
public interface OptLogServiceRpc {

	/**
	 * 增加操作日志
	 * @param optLog 操作日志实体
	 * @return JsonData
	 */
	@PostMapping
	JsonData insertOptLog(@RequestBody OptLog optLog);
}

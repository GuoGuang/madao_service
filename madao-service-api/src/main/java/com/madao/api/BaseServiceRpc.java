package com.madao.api;

import com.madao.constant.FeignConst;
import com.madao.fallback.BaseServiceRpcFallbackFactory;
import com.madao.model.entity.base.LoginLog;
import com.madao.model.entity.base.OptLog;
import com.madao.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 操作日志api
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@FeignClient(contextId = FeignConst.SERVICE_BASE,
		value = FeignConst.SERVICE_BASE,
		fallbackFactory = BaseServiceRpcFallbackFactory.class)
public interface BaseServiceRpc {

	/**
	 * 增加操作日志
	 *
	 * @param optLog 操作日志实体
	 * @return JsonData
	 */
	@PostMapping(FeignConst.SERVICE_BASE_OPT_LOG_PATH)
	JsonData<Object> insertOptLog(@RequestBody OptLog optLog);

	/**
	 * 增加登录日志
	 *
	 * @param loginLog 登录日志实体
	 * @return JsonData
	 */
	@PostMapping(FeignConst.SERVICE_BASE_LOGIN_LOG_PATH)
	JsonData<Void> insertLoginLog(@RequestBody LoginLog loginLog);

}

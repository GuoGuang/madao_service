package com.youyd.api.base;

import com.youyd.api.base.fallback.LoginLogServiceRpcFallbackFactory;
import com.youyd.constant.FeignConst;
import com.youyd.pojo.base.LoginLog;
import com.youyd.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 登录日志api
 * @author : LGG
 * @create : 2018-09-27
 **/

@FeignClient(value = FeignConst.SERVICE_BASE,path = FeignConst.SERVICE_BASE_PATH,fallbackFactory = LoginLogServiceRpcFallbackFactory.class)
public interface LoginLogServiceRpc {

	/**
	 * 增加登录日志
	 * @param loginLog 登录日志实体
	 * @return JsonData
	 */
	@PostMapping
	JsonData insertLoginLog(@RequestBody LoginLog loginLog);
}

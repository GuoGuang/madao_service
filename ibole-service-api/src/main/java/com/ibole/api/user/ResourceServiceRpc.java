package com.ibole.api.user;

import com.ibole.FeignClientConfig;
import com.ibole.constant.FeignConst;
import com.ibole.fallback.user.ResourceServiceRpcFallbackFactory;
import com.ibole.pojo.user.Resource;
import com.ibole.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 资源服务api
 * @author : LGG
 * @create : 2019-09-03
 **/

@FeignClient(value = FeignConst.SERVICE_USER,
		path = FeignConst.SERVICE_RESOURCE_PATH,
		fallbackFactory = ResourceServiceRpcFallbackFactory.class,
		configuration = { FeignClientConfig.class })
public interface ResourceServiceRpc {

	/**
	 * 条件查询资源
	 * @param resource
	 * @return
	 */
	@GetMapping
	JsonData<List<Resource>> findResourceByCondition(Resource resource);

	@GetMapping("/roles")
	JsonData<List<Resource>> findResourceByRoleIds(@RequestParam("roleId") String[] roleIds);
}

package com.codeway.api.user;

import com.codeway.FeignClientConfig;
import com.codeway.constant.FeignConst;
import com.codeway.fallback.user.UserServiceRpcFallbackFactory;
import com.codeway.model.dto.user.UserDto;
import com.codeway.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 用户服务api
 **/

@FeignClient(contextId = "userClient",
		value = FeignConst.SERVICE_USER,
		path = FeignConst.SERVICE_USER_PATH,
		fallbackFactory = UserServiceRpcFallbackFactory.class,
		configuration = {FeignClientConfig.class})
public interface UserServiceRpc {

	@GetMapping("/info")
	JsonData<UserDto> getUserInfo(UserDto account);


}
package com.madaoo.api.user;

import com.madaoo.FeignClientConfig;
import com.madaoo.constant.FeignConst;
import com.madaoo.fallback.user.UserServiceRpcFallbackFactory;
import com.madaoo.model.dto.user.UserDto;
import com.madaoo.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
	JsonData<UserDto> getUserInfo(@RequestParam("account") String account);

	@GetMapping("/{userId}")
	JsonData<UserDto> getUserInfoById(@PathVariable("userId") String userId);

	@GetMapping("/all")
	JsonData<List<UserDto>> getUserInfoByIds(@RequestParam("userIds") String[] userIds);

}
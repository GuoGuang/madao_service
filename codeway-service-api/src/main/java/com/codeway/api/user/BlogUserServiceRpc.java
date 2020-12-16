package com.madaoo.api.user;

import com.madaoo.FeignClientConfig;
import com.madaoo.constant.FeignConst;
import com.madaoo.fallback.user.BlogUserServiceRpcFallbackFactory;
import com.madaoo.model.dto.user.UserDto;
import com.madaoo.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * 博客用户服务api
 **/

@FeignClient(contextId = "blogUserClient",
		value = FeignConst.SERVICE_USER,
		path = FeignConst.SERVICE_BLOG_USER_PATH,
		fallbackFactory = BlogUserServiceRpcFallbackFactory.class,
		configuration = {FeignClientConfig.class})
public interface BlogUserServiceRpc {

	@PostMapping("/login/github")
	JsonData<UserDto> loginWithGithub(@RequestBody Map<String, Object> userInfo);
}
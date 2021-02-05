package com.madao.api.user;

import com.madao.FeignClientConfig;
import com.madao.constant.FeignConst;
import com.madao.fallback.user.BlogUserServiceRpcFallbackFactory;
import com.madao.model.dto.user.UserDto;
import com.madao.utils.JsonData;
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
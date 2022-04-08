package com.madao.api;

import com.madao.FeignClientConfig;
import com.madao.constant.FeignConst;
import com.madao.fallback.UserServiceRpcFallbackFactory;
import com.madao.model.dto.user.UserDto;
import com.madao.model.entity.user.Resource;
import com.madao.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户服务api
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@FeignClient(contextId = FeignConst.SERVICE_USER,
        value = FeignConst.SERVICE_USER,
        fallbackFactory = UserServiceRpcFallbackFactory.class,
        configuration = {FeignClientConfig.class})
public interface UserServiceRpc {

    @GetMapping(FeignConst.SERVICE_USER_PATH+"/info")
    JsonData<UserDto> getUserInfo(@RequestParam("account") String account);

    @GetMapping(FeignConst.SERVICE_USER_PATH)
    JsonData<UserDto> register(@RequestBody UserDto userDto);

    @GetMapping(FeignConst.SERVICE_USER_PATH+"/{userId}")
    JsonData<UserDto> getUserInfoById(@PathVariable("userId") String userId);

    @GetMapping(FeignConst.SERVICE_USER_PATH+"/all")
    JsonData<List<UserDto>> getUserInfoByIds(@RequestParam("userIds") String[] userIds);

	/**
	 * 条件查询资源
	 */
	@GetMapping(FeignConst.SERVICE_RESOURCE_PATH)
	JsonData<List<Resource>> findResourceByCondition();

	@GetMapping(FeignConst.SERVICE_RESOURCE_PATH+"/roles")
	JsonData<List<Resource>> findResourceByRoleIds(@RequestParam("roleId") String[] roleIds);

	@PostMapping(FeignConst.SERVICE_BLOG_USER_PATH + "/login/github")
	JsonData<UserDto> loginWithGithub(@RequestBody Map<String, Object> userInfo);
}
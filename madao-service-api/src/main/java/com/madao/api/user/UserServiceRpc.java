package com.madao.api.user;

import com.madao.FeignClientConfig;
import com.madao.constant.FeignConst;
import com.madao.fallback.user.UserServiceRpcFallbackFactory;
import com.madao.model.dto.user.UserDto;
import com.madao.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户服务api
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
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
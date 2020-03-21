package com.codeway.api.user;

import com.codeway.FeignClientConfig;
import com.codeway.config.CustomQueryResults;
import com.codeway.constant.FeignConst;
import com.codeway.fallback.user.UserServiceRpcFallbackFactory;
import com.codeway.pojo.user.User;
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

    /**
     * 按照user查询
     *
     * @param user user
     * @return JsonData
     */
    @GetMapping
    JsonData<CustomQueryResults<User>> findUserByUser(User user);

    @GetMapping("/account")
    JsonData<User> findUserByAccount(User account);

    /**
     * 查询所有用户
     *
     * @return JsonData
     */
    @GetMapping
    JsonData<CustomQueryResults<User>> findUser();

}
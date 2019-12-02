package com.ibole.api.user;

import com.ibole.FeignClientConfig;
import com.ibole.config.CustomPage;
import com.ibole.constant.FeignConst;
import com.ibole.fallback.user.UserServiceRpcFallbackFactory;
import com.ibole.pojo.user.User;
import com.ibole.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 用户服务api
 **/

@FeignClient(value = FeignConst.SERVICE_USER,
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
    JsonData<CustomPage<User>> findUserByUser(User user);

    @GetMapping("/account")
    JsonData<User> findUserByAccount(User account);

    /**
     * 查询所有用户
     *
     * @return JsonData
     */
    @GetMapping
    JsonData<CustomPage<User>> findUser();

}
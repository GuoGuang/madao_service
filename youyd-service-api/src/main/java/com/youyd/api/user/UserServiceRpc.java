package com.youyd.api.user;

import com.youyd.constant.FeignConst;
import com.youyd.fallback.user.UserServiceRpcFallbackFactory;
import com.youyd.pojo.user.User;
import com.youyd.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务api
 * @author : LGG
 * @create : 2019-06-11
 **/

@FeignClient(value = FeignConst.SERVICE_USER,path = FeignConst.SERVICE_USER_PATH,fallbackFactory = UserServiceRpcFallbackFactory.class)
public interface UserServiceRpc {

	/**
	 * 登录
	 * @param request 请求
	 * @return JsonData
	 */
	@PostMapping(value = "/login")
	JsonData login(HttpServletRequest request);

	/**
	 * 按照user查询
	 * @param user user
	 * @return JsonData
	 */
	@PostMapping(value = "/condition")
	JsonData<User> findUserByUser(@RequestParam("account") String userName);

}
package com.ibole.api.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ibole.FeignClientConfig;
import com.ibole.constant.FeignConst;
import com.ibole.fallback.user.UserServiceRpcFallbackFactory;
import com.ibole.pojo.user.User;
import com.ibole.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务api
 **/

@FeignClient(value = FeignConst.SERVICE_USER,
		path = FeignConst.SERVICE_USER_PATH,
		fallbackFactory = UserServiceRpcFallbackFactory.class,
		configuration = { FeignClientConfig.class })
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
	@GetMapping
	JsonData<Page<User>> findUserByUser(User user);

	/**
	 * 查询所有用户
	 * @return JsonData
	 */
	@GetMapping
	JsonData<Page<User>> findUser();

}
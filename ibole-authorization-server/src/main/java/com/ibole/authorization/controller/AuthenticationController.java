package com.ibole.authorization.controller;

import com.ibole.authorization.service.AuthenticationService;
import com.ibole.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 鉴权控制器
 **/
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

	/**
	 * 根据url,method 验证当前用户是否有操作权限
	 * @param url 请求地址
	 * @param method 请求方法
	 * @param request request
	 * @return JsonData
	 */
	@PostMapping(value = "/permission")
    public JsonData decide(@RequestParam String url, @RequestParam String method, HttpServletRequest request) {
		boolean decide = authenticationService.decide(new HttpServletRequestAuthWrapper(request, url, method));
        return JsonData.success(decide);
    }

}
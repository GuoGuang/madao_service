package com.youyd.authorization.controller;

import com.youyd.authorization.service.AuthenticationService;
import com.youyd.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 鉴权控制器
 * @author : LGG
 * @create : 2019-06-12
 **/
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

	/**
	 * 验证权限
	 * @param url
	 * @param method
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/permission")
    //public JsonData decide(@RequestParam String url, @RequestParam String method, HttpServletRequest request) {
    public JsonData decide(HttpServletRequest request) {
        //boolean decide = authenticationService.decide(new HttpServletRequestAuthWrapper(request, url, method));
        return JsonData.success(true);
    }

}
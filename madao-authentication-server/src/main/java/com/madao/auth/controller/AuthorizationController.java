package com.madao.auth.controller;

import cn.hutool.json.JSONObject;
import com.madao.auth.config.HttpServletRequestAuthWrapper;
import com.madao.auth.service.AuthorizationService;
import com.madao.utils.JsonData;
import com.madao.utils.security.JWTAuthentication;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api("鉴权")
@RequestMapping("/oauth")
@RestController
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    /**
     * 根据url,method 验证当前用户是否有操作权限
     *
     * @param url     请求地址
     * @param method  请求方法
     * @param request request
     * @return JsonData
     */
    @PostMapping(value = "/permission")
    @ApiOperation(value = "根据url,method 验证当前用户是否有操作权限", notes = "Auth")
    public JsonData<Boolean> decide(@RequestParam String url, @RequestParam String method, HttpServletRequest request) {
        JSONObject token = JWTAuthentication.parseJwtToClaimsAsJSONObject(request.getHeader(HttpHeaders.AUTHORIZATION));
        return JsonData.success(authorizationService.decide(new HttpServletRequestAuthWrapper(request, url, method),token));
    }

}
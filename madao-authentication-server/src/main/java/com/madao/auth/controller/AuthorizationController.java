package com.madao.auth.controller;

import cn.hutool.json.JSONObject;
import com.madao.auth.config.HttpServletRequestAuthWrapper;
import com.madao.auth.service.AuthorizationService;
import com.madao.utils.JsonData;
import com.madao.utils.security.JWTAuthentication;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Tag(name = "鉴权")
@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthorizationController {

	private final AuthorizationService authorizationService;

	/**
	 * 根据url,method 验证当前用户是否有操作权限
	 * <p>
	 * 在单体服务中可以通过自定义 实现 FilterInvocationSecurityMetadataSource获取用户权限，然后通过实现 AccessDecisionManager的decide方法判断
	 * 用户是否有权限。
	 *
	 * @param url     请求地址
	 * @param method  请求方法
	 * @param request request
	 * @return JsonData
	 * @see https://github.com/lenve/vhr/blob/master/vhr/vhrserver/vhr-web/src/main/java/org/javaboy/vhr/config/CustomFilterInvocationSecurityMetadataSource.java
	 */
	@PostMapping(value = "/permission")
	@Operation(summary = "根据url,method 验证当前用户是否有操作权限", description = "Auth")
	public JsonData<Boolean> decide(@RequestParam String url, @RequestParam String method, HttpServletRequest request) {
		JSONObject token = JWTAuthentication.parseJwtToClaimsAsJSONObject(request.getHeader(HttpHeaders.AUTHORIZATION));
		return JsonData.success(authorizationService.decide(new HttpServletRequestAuthWrapper(request, url, method), token));
	}

}
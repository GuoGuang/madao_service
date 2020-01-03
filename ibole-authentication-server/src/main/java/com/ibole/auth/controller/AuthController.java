package com.ibole.auth.controller;

import com.ibole.auth.service.AuthService;
import com.ibole.auth.validate.ValidateCodeProcessor;
import com.ibole.auth.validate.ValidateCodeProcessorHolder;
import com.ibole.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api("用户登录认证")
@RestController
@RequestMapping("/oauth")
public class AuthController  {

	private AuthService authService;

	private final ValidateCodeProcessorHolder validateCodeProcessorHolder;

	public AuthController(AuthService authService, ValidateCodeProcessorHolder validateCodeProcessorHolder) {
		this.authService = authService;
		this.validateCodeProcessorHolder = validateCodeProcessorHolder;
	}

	@PostMapping(value = "/logout")
	@ApiOperation(value = "登出系统", notes = "Auth")
	@ApiImplicitParams(
			@ApiImplicitParam(name = "token", value = "令牌", dataType = "String", paramType = "header")
	)
	public JsonData<Void> logout(@RequestHeader("AUTH") String token) {
		authService.logout(token);
		return JsonData.success();
	}

	/**
	 * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
	 * 根据实现使用HttpServletResponse写回浏览器
	 */
	@GetMapping("/code/{type}")
	@ApiOperation(value = "根据不同类型获取验证码", notes = "Auth")
	@ApiImplicitParams(
			@ApiImplicitParam(name = "type", value = "验证码类型，captcha：图片验证码，sms：手机验证码", dataType = "String", paramType = "path")
	)
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)throws Exception {
		ServletWebRequest servletWebRequest = new ServletWebRequest(request, response);
		ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorHolder.findValidateCodeProcessor(type);
		validateCodeProcessor.create(servletWebRequest);
	}

}

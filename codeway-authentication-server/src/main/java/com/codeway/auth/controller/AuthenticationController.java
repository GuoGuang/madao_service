package com.codeway.auth.controller;

import com.codeway.auth.service.AuthenticationService;
import com.codeway.auth.validate.ValidateCodeProcessor;
import com.codeway.auth.validate.ValidateCodeProcessorHolder;
import com.codeway.utils.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api("用户登录认证")
@RestController
@RequestMapping("/oauth")
public class AuthenticationController {

	private AuthenticationService authenticationService;

	private final ValidateCodeProcessorHolder validateCodeProcessorHolder;

	public AuthenticationController(AuthenticationService authenticationService, ValidateCodeProcessorHolder validateCodeProcessorHolder) {
		this.authenticationService = authenticationService;
		this.validateCodeProcessorHolder = validateCodeProcessorHolder;
	}

	@PostMapping(value = "/logout")
	@ApiOperation(value = "登出系统", notes = "Auth")
	@ApiImplicitParams(
			@ApiImplicitParam(name = "token", value = "令牌", dataType = "String", paramType = "header")
	)
	public JsonData<Void> logout(@RequestHeader("AUTH") String token) {
		authenticationService.logout(token);
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
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws IOException, ServletRequestBindingException {
		ServletWebRequest servletWebRequest = new ServletWebRequest(request, response);
		ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorHolder.findValidateCodeProcessor(type);
		validateCodeProcessor.create(servletWebRequest);
	}

}

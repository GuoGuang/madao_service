package com.madaoo.auth.controller;

import com.madaoo.auth.validate.ValidateCodeProcessor;
import com.madaoo.auth.validate.ValidateCodeProcessorHolder;
import com.madaoo.db.redis.service.RedisService;
import com.madaoo.utils.JsonData;
import com.madaoo.utils.security.JWTAuthentication;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

	private final RedisService redisService;

	private final ValidateCodeProcessorHolder validateCodeProcessorHolder;

	public AuthenticationController(ValidateCodeProcessorHolder validateCodeProcessorHolder,
	                                RedisService redisService) {
		this.validateCodeProcessorHolder = validateCodeProcessorHolder;
		this.redisService = redisService;
	}


	@PostMapping(value = "/logout")
	@ApiOperation(value = "登出系统", notes = "Auth")
	@ApiImplicitParams(
			@ApiImplicitParam(name = "token", value = "令牌", dataType = "String", paramType = "header")
	)
	@GetMapping(value = "/logout")
	public JsonData<Void> logout(HttpServletRequest request, HttpServletResponse response,
	                             @RequestHeader("AUTH") String token) {
		redisService.del("user_token:" + JWTAuthentication.getFullAuthorization(token));
		new SecurityContextLogoutHandler().logout(request, response, null);
		return JsonData.success();
	}

	/**
	 * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
	 * 根据实现使用HttpServletResponse写回浏览器
	 *
	 * @param type: ValidateCodeProcessor 子类前缀
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

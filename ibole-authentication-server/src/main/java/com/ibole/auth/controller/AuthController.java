package com.ibole.auth.controller;

import com.ibole.auth.service.AuthService;
import com.ibole.auth.validate.ValidateCodeProcessor;
import com.ibole.auth.validate.ValidateCodeProcessorHolder;
import com.ibole.enums.StatusEnum;
import com.ibole.utils.JsonData;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录认证
 **/
@RestController
@RequestMapping("/oauth")
public class AuthController  {

	private AuthService authService;

	private final ValidateCodeProcessorHolder validateCodeProcessorHolder;

	public AuthController(AuthService authService, ValidateCodeProcessorHolder validateCodeProcessorHolder) {
		this.authService = authService;
		this.validateCodeProcessorHolder = validateCodeProcessorHolder;
	}

	/**
	 * 登出系统
	 * @param token 令牌
	 */
	@PostMapping(value = "/logout")
    public JsonData logout(@RequestHeader("AUTH")String token) {
		authService.logout(token);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
    }

	/**
	 * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
	 * 根据实现使用HttpServletResponse写回浏览器
	 * @param type captcha：图片验证码，sms：手机验证码
	 */
	@GetMapping("/code/{type}")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)throws Exception {
		ServletWebRequest servletWebRequest = new ServletWebRequest(request, response);
		ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorHolder.findValidateCodeProcessor(type);
		validateCodeProcessor.create(servletWebRequest);
	}

}

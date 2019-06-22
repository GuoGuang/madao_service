package com.youyd.auth.controller;

import com.youyd.auth.service.AuthService;
import com.youyd.auth.validate.ValidateCodeProcessor;
import com.youyd.auth.validate.ValidateCodeProcessorHolder;
import com.youyd.cache.redis.RedisService;
import com.youyd.enums.StatusEnum;
import com.youyd.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录认证
 * @author : LGG
 * @create : 2019-06-18 14:34
 **/
@RestController
@RequestMapping("/oauth")
public class AuthController  {

    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String clientSecret;
    @Value("${auth.cookieDomain}")
    String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;

    @Autowired
    AuthService authService;

    @Autowired
    RedisService redisService;

	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;

	/**
	 * 登出系统
	 * @param token 令牌
	 */
	@PostMapping(value = "/logout")
	//@OptLog(operationType= CommonConst.MODIFY,operationName="退出系统")
    public JsonData logout(@RequestHeader("AUTH")String token) {
		authService.logout(token);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
    }

	/**
	 * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
	 * @param request
	 * @param response
	 * @param type
	 * @throws Exception
	 */
	@GetMapping("/code/{type}")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)throws Exception {
		ServletWebRequest servletWebRequest = new ServletWebRequest(request, response);
		ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorHolder.findValidateCodeProcessor(type);
		validateCodeProcessor.create(servletWebRequest);
		//return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 获取图片验证码;
	 * 每次访问生成uuid和验证码存入redis,登录时根据uuid到redis中获取验证码对比
	/* *//*
	@GetMapping("/captcha")
	@ResponseBody
	public JsonData createCode() {
		String uuid = UUID.randomUUID().toString();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String captcha = new CaptchaCode().createImageOnPage(outputStream);
		String base64Code = Base64.getEncoder().encodeToString(outputStream.toByteArray());
		redisService.set(uuid,captcha, CommonConst.TIME_OUT_HALF_HOUR);
		HashMap<Object, Object> map = new HashMap<>();
		map.put("captchaUuid",uuid);
		map.put("base64Code",base64Code);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(),map);

	}*/

}

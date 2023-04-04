package com.madao.exception;

import com.madao.exception.custom.*;
import com.madao.utils.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一业务处理类
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BusinessExceptionHandle {

	@ExceptionHandler(UserException.class)
	public JsonData<Void> userException(UserException ex) {
		log.error(ex.getMessage(), ex);
		return JsonData.failed(ex);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public JsonData<Void> resourceNotFoundException(ResourceNotFoundException ex) {
		log.error(ex.getMessage(), ex);
		return JsonData.failed(ex);
	}

	@ExceptionHandler(PhoneExistingException.class)
	public JsonData<Void> phoneExistingException(PhoneExistingException ex) {
		log.error(ex.getMessage(), ex);
		return JsonData.failed(ex);
	}

	@ExceptionHandler(CaptchaNotMatchException.class)
	public JsonData<Void> captchaNotMatchException(CaptchaNotMatchException ex) {
		log.error(ex.getMessage(), ex);
		return JsonData.failed(ex);
	}

	@ExceptionHandler(RateLimiterException.class)
	public JsonData<Void> captchaNotMatchException(RateLimiterException ex) {
		log.error("限流业务异常：{}", ex.getMessage(), ex);
		return JsonData.failed(ex);
	}

}

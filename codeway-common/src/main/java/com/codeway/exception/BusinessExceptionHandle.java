package com.madaoo.exception;

import com.madaoo.exception.custom.*;
import com.madaoo.utils.JsonData;
import com.madaoo.utils.LogBack;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一业务处理类
 **/
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BusinessExceptionHandle {

	@ExceptionHandler(UserException.class)
	public JsonData<Void> userException(UserException ex) {
		LogBack.error(ex.getMessage(), ex);
		return JsonData.failed(ex);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public JsonData<Void> resourceNotFoundException(ResourceNotFoundException ex) {
		LogBack.error(ex.getMessage(), ex);
		return JsonData.failed(ex);
	}

	@ExceptionHandler(PhoneExistingException.class)
	public JsonData<Void> phoneExistingException(PhoneExistingException ex) {
		LogBack.error(ex.getMessage(), ex);
		return JsonData.failed(ex);
	}

	@ExceptionHandler(CaptchaNotMatchException.class)
	public JsonData<Void> captchaNotMatchException(CaptchaNotMatchException ex) {
		LogBack.error(ex.getMessage(), ex);
		return JsonData.failed(ex);
	}

}

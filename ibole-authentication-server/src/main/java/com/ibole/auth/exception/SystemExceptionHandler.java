package com.ibole.auth.exception;

import com.ibole.enums.StatusEnum;
import com.ibole.utils.JsonData;
import com.ibole.utils.LogBack;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一系统异常处理类
 **/
@RestControllerAdvice
public class SystemExceptionHandler {

	/**
	 * ValidateCodeException
	 *
	 * @param ex ValidateCodeException
	 */
	@ExceptionHandler(ValidateCodeException.class)
	public JsonData validateCodeException(ValidateCodeException ex) {
		LogBack.error(ex.getMessage(), ex);
		return new JsonData(StatusEnum.PARAM_ILLEGAL);
	}

}

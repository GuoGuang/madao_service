package com.codeif.exception;

import com.codeif.exception.custom.ResourceNotFoundException;
import com.codeif.exception.custom.UserException;
import com.codeif.utils.JsonData;
import com.codeif.utils.LogBack;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一业务处理类
 **/
@RestControllerAdvice
public class BusinessExceptionHandle {
	/**
	 * 用户相关异常
	 *
	 * @param ex IllegalArgumentException
	 */
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

}

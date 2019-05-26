package com.youyd.user.exception;

import com.youyd.enums.StatusEnum;
import com.youyd.utils.JsonData;
import com.youyd.utils.LogBack;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: GlobalExceptionHandler </p>
 * <p>Description: 全局异常处理器</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public JsonData handleException(HttpServletRequest request, Exception ex) {
		LogBack.error(ex.getMessage(),ex);
		return new JsonData(false, StatusEnum.SYSTEM_EXCEPTION.getCode(),ex.getMessage());
	}

}

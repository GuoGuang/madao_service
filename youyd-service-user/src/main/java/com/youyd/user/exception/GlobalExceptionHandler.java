package com.youyd.user.exception;

import com.youyd.utils.JSONData;
import com.youyd.utils.LogBack;
import com.youyd.utils.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: GlobalExceptionHandler </p>
 * <p>Description: 全局异常处理器</p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public JSONData handleException(HttpServletRequest request, Exception ex) {
		LogBack.error(ex.getMessage(),ex);
		return new JSONData(false, StatusCode.SYSTEM_EXCEPTION.getCode(),ex.getMessage());
	}

}

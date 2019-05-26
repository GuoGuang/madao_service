package com.youyd.base.exception;

import com.youyd.enums.StatusEnum;
import com.youyd.utils.JsonData;
import com.youyd.utils.LogBack;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

/**
 * 统一异常处理类
 * 我把异常根据意义成三种：
 *      1. 业务异常
 *      2. 系统异常
 *      3. 代码异常
 * 不同的异常采用不同的处理方式。
 *
 * @author : LGG
 * @create : 2018-09-26 16:06
 **/
@RestControllerAdvice
public class BaseExceptionHandler {

	/**
	 * 系统抛出ERROR异常
	 * @param ex 异常信息
	 */
	@ExceptionHandler(Throwable.class)
	public JsonData handleOtherException(Throwable ex) {
		return new JsonData(false,StatusEnum.UNKNOWN.getCode(), ex.getMessage());
	}

	/**
	 * 当抛出非自定义异常时执行此处理器
	 * @param ex 异常信息
	 */
	@ExceptionHandler(value = Exception.class)
	public JsonData HandleException(Exception ex) {
		LogBack.error(ex.getMessage(),ex);
		return new JsonData(false, StatusEnum.ERROR.getCode(), ex.getMessage());
	}

	/**
	 * 业务异常：资源不存在时
	 * @param ex 异常信息
	 */
	@ExceptionHandler(value = ResourceAccessException.class)
	public JsonData ResourceAccessExceptionException(Exception ex) {
		LogBack.error(ex.getMessage(),ex);
		return new JsonData(false, StatusEnum.ERROR.getCode(), ex.getMessage());
	}
}

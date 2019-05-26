package com.youyd.search.exception;

import com.youyd.enums.StatusEnum;
import com.youyd.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description: 统一异常处理类
 * @author: LGG
 * @create: 2018-09-26 16:06
 **/
@RestControllerAdvice
public class BaseExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result error(Exception e) {
		e.printStackTrace();
		return new Result(false, StatusEnum.ERROR.getCode(), e.getMessage());
	}
}

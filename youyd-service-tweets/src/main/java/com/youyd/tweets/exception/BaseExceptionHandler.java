package com.youyd.tweets.exception;

import com.youyd.customexception.ValidFieldError;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.ErrotResult;
import com.youyd.utils.LogBack;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.UnexpectedTypeException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 统一异常处理类
 * @author: LGG
 * @create: 2018-09-26 16:06
 **/
@RestControllerAdvice
@SuppressWarnings("Duplicates")
public class BaseExceptionHandler {


	/**
	 * 参数不合法错误
	 * @param e IllegalArgumentException
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ErrotResult illegalArgumentException(IllegalArgumentException ex) {
		LogBack.error(ex.getMessage(),ex);
		return new ErrotResult(StatusEnum.PARAM_ILLEGAL);
	}

	/**
	 * 缺少请求参数错误
	 * @param e missingServletRequestParameterException
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ErrotResult missingServletRequestParameterException(MissingServletRequestParameterException ex) {
		LogBack.error(ex.getMessage(),ex);
		return new ErrotResult(StatusEnum.PARAM_MISSING);
	}

	/**
	 * 请求类型错误
	 * @param e HttpRequestMethodNotSupportedException
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ErrotResult httpRequestMethodNotSupportedException(Exception ex) {
		LogBack.error(ex.getMessage(),ex);
		return new ErrotResult(StatusEnum.REQUEST_ERROR);
	}

	/**
	 * JSR303参数校验错误
	 * @param e BindException
	 */
	@ExceptionHandler(BindException.class)
	public ErrotResult bindException(Exception ex) {
		LogBack.error(ex.getMessage(),ex);
		BindingResult bindingResult = (ex instanceof BindException) ? ((BindException)ex).getBindingResult()
				: ((MethodArgumentNotValidException)ex).getBindingResult();
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			List<ValidFieldError> validList = new ArrayList<>();
			if (!(CollectionUtils.isEmpty(errors))) {
				for (FieldError fe : errors) {
					validList.add(new ValidFieldError(fe));
				}
			}
			LogBack.error("参数校验错误："+validList.toString(),ex);
			return new ErrotResult(StatusEnum.PARAM_INVALID, validList.toString());
		}
		return new ErrotResult(StatusEnum.PARAM_INVALID);
	}

	/**
	 * 其他异常
	 * @param e Exception
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ErrotResult defaultException(Exception ex) {
		LogBack.error(ex.getMessage(),ex);
		if ((ex instanceof BindException) || (ex instanceof MethodArgumentNotValidException) || (ex instanceof UnexpectedTypeException)) {
			return bindException(ex);
		}
		return new ErrotResult(StatusEnum.SYSTEM_ERROR);
	}


}

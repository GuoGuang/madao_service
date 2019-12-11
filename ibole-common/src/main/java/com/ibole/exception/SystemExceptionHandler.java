package com.ibole.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ibole.exception.custom.RemoteRpcException;
import com.ibole.exception.custom.ParamException;
import com.ibole.exception.custom.ValidFieldError;
import com.ibole.enums.StatusEnum;
import com.ibole.utils.JsonData;
import com.ibole.utils.LogBack;
import com.netflix.client.ClientException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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

import javax.servlet.Servlet;
import javax.validation.UnexpectedTypeException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

/**
 * 统一系统异常处理类
 *
 * Add ConditionalOnBean(Servlet.class) fix Exception Caused by: java.lang.ClassNotFoundException: javax.servlet.ServletException
 * 	Because spring-cloud-starter-gateway conflicts with javax.servlet.Servlet
 **/
@ConditionalOnBean(Servlet.class)
@RestControllerAdvice
public class SystemExceptionHandler {

	/**
	 * 参数不合法错误
	 * @param ex IllegalArgumentException
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public JsonData illegalArgumentException(IllegalArgumentException ex) {
		LogBack.error(ex.getMessage(),ex);
		return new JsonData(StatusEnum.PARAM_ILLEGAL);
	}

	/**
	 * 缺少请求参数错误
	 * @param ex MissingServletRequestParameterException
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public JsonData missingServletRequestParameterException(MissingServletRequestParameterException ex) {
		LogBack.error(ex.getMessage(),ex);
		return new JsonData(StatusEnum.PARAM_MISSING);
	}

	/**
	 * 请求类型错误
	 * @param ex HttpRequestMethodNotSupportedException
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public JsonData httpRequestMethodNotSupportedException(Exception ex) {
		LogBack.error(ex.getMessage(),ex);
		return new JsonData(StatusEnum.REQUEST_ERROR);
	}

	/**
	 * JSR303参数校验错误
	 * @param ex BindException
	 */
	@ExceptionHandler(BindException.class)
	public JsonData bindException(Exception ex) {
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
            return new JsonData(false, StatusEnum.PARAM_INVALID, validList.toString());
		}
		return new JsonData(StatusEnum.PARAM_INVALID);
	}

	/**
	 * 参数异常
	 * @param ex Exception
	 */
	@ExceptionHandler(ParamException.class)
	@ResponseBody
	public JsonData paramException(ParamException ex) {
		LogBack.error(ex.getMessage(),ex);
		return new JsonData(StatusEnum.PARAM_ILLEGAL);
	}

    /**
     * 远程RPC调用异常
     *
     * @param ex IllegalArgumentException
     */
    @ExceptionHandler(RemoteRpcException.class)
    public JsonData remoteRpcException(RemoteRpcException ex) {
        String message = ex.getMessage();
        LogBack.error(ex.getMessage(), ex);
        return new JsonData(StatusEnum.RPC_ERROR);
    }

    /**
     * url未在资源池中
     *
     * @param ex AccessDeniedException
     */
    @ExceptionHandler(AccessDeniedException.class)
    public JsonData missingServletRequestParameterException(AccessDeniedException ex) {
        LogBack.error(ex.getMessage(), ex);
        return new JsonData(StatusEnum.ACCESS_DENIED);
    }

	/**
	 * JWT失效异常
	 * @param ex Exception
	 */
	@ExceptionHandler(TokenExpiredException.class)
	public JsonData tokenExpiredException(TokenExpiredException ex) {
		LogBack.error(ex.getMessage(),ex);
		return new JsonData(StatusEnum.LOGIN_EXPIRED);
	}

	/**
	 * Load balancer does not have available server for client
	 * @param ex Exception
	 */
	@ExceptionHandler(ClientException.class)
    public JsonData clientException(ClientException ex) {
        LogBack.error(ex.getMessage(),ex);
        return new JsonData(StatusEnum.SERVICE_OFF);
    }

	/**
	 * 其他异常
	 * @param ex Exception
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonData defaultException(Exception ex) {
		LogBack.error(ex.getMessage(),ex);
		if ((ex instanceof BindException) || (ex instanceof MethodArgumentNotValidException) || (ex instanceof UnexpectedTypeException)) {
			return bindException(ex);
		}
		return new JsonData(StatusEnum.SYSTEM_ERROR);
	}


}

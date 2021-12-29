package com.madao.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.madao.enums.StatusEnum;
import com.madao.exception.custom.ParamException;
import com.madao.exception.custom.RemoteRpcException;
import com.madao.exception.custom.ValidFieldError;
import com.madao.model.entity.JsonException;
import com.madao.utils.JsonData;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 统一系统异常处理类
 * Add ConditionalOnBean(Servlet.class) fix Exception Caused by: java.lang.ClassNotFoundException: javax.servlet.ServletException
 * Because spring-cloud-starter-gateway conflicts with javax.servlet.Servlet
 * //@ConditionalOnBean(Servlet.class)
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@RestControllerAdvice
public class SystemExceptionHandler {

    /**
     * 参数不合法错误
     *
     * @param ex IllegalArgumentException
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public JsonData<Void> illegalArgumentException(IllegalArgumentException ex) {
        log.error(ex.getMessage(), ex);
        return JsonData.failed(StatusEnum.PARAM_ILLEGAL);
    }

    /**
     * 缺少请求参数错误
     *
     * @param ex MissingServletRequestParameterException
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JsonData<Void> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error(ex.getMessage(), ex);
        return JsonData.failed(StatusEnum.PARAM_MISSING);
    }

    /**
     * 请求类型错误
     *
     * @param ex HttpRequestMethodNotSupportedException
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonData<Void> httpRequestMethodNotSupportedException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return JsonData.failed(StatusEnum.REQUEST_ERROR);
    }

    /**
     * JSR303参数校验未通过
     *
     * @param ex BindException
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public JsonData<Void> bindException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        BindingResult bindingResult = ex.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            List<ValidFieldError> validList = new ArrayList<>();
            if (!(CollectionUtils.isEmpty(errors))) {
                for (FieldError fe : errors) {
                    validList.add(new ValidFieldError(fe));
                }
            }
            log.error("参数校验未通过：" + validList, ex);
            return JsonData.failed(StatusEnum.PARAM_INVALID, validList.toString());
        }
        return JsonData.failed(StatusEnum.PARAM_INVALID);
    }

    /**
     * 参数异常
     *
     * @param ex Exception
     */
    @ExceptionHandler(ParamException.class)
    @ResponseBody
    public JsonData<Void> paramException(ParamException ex) {
        log.error(ex.getMessage(), ex);
        return JsonData.failed(StatusEnum.PARAM_ILLEGAL);
    }

    /**
     * 远程RPC调用异常
     *
     * @param ex IllegalArgumentException
     */
    @ExceptionHandler(RemoteRpcException.class)
    public JsonData<Void> remoteRpcException(RemoteRpcException ex) {
        log.error(ex.getMessage(), ex);
        return JsonData.failed(StatusEnum.RPC_ERROR);
    }

    /**
     * url未在资源池中
     *
     * @param ex AccessDeniedException
     */
    @ExceptionHandler(AccessDeniedException.class)
    public JsonData<Void> missingServletRequestParameterException(AccessDeniedException ex) {
        log.error(ex.getMessage(), ex);
        return JsonData.failed(StatusEnum.UN_AUTHORIZED);
    }

    /**
     * JWT失效异常
     *
     * @param ex Exception
     */
    @ExceptionHandler(TokenExpiredException.class)
    public JsonData<Void> tokenExpiredException(TokenExpiredException ex) {
        log.error(ex.getMessage(), ex);
        return JsonData.failed(StatusEnum.LOGIN_EXPIRED);
    }

    /**
     * Load balancer does not have available server for client
     *
     * @param ex Exception
     */
    @ExceptionHandler(RetryableException.class)
    public JsonData<Void> clientException(RetryableException ex) {
        log.error(ex.getMessage(), ex);
        return JsonData.failed(StatusEnum.SERVICE_OFF);
    }

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public JsonData<Void> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
		log.error("不匹配的输入异常：----------->{}", ex.getMessage(),ex);
		return JsonData.failed(StatusEnum.PARAM_ILLEGAL,"不匹配的输入异常："+ex.getMessage());
	}

	@ExceptionHandler(DuplicateKeyException.class)
	@ResponseBody
	public JsonData<Void> duplicateKeyException(DuplicateKeyException ex) {
		log.error("唯一索引：{}：{}", StatusEnum.DUPLICATE_KEY.getMsg(),ex.getMessage(), ex);
		return JsonData.failed(StatusEnum.DUPLICATE_KEY);
	}

	@ExceptionHandler(JsonException.class)
	@ResponseBody
	public JsonData<Void> jsonException(JsonException ex) {
		log.error("Jackson序列化异常：{}",ex.getMessage(), ex);
		return JsonData.failed(StatusEnum.SYSTEM_ERROR);
	}

    /**
     * 其他异常
     *
     * @param ex Exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonData<Void> defaultException(Exception ex) {
        log.error("其他异常--------->{}", ex.getMessage(), ex);
        return JsonData.failed(ex);
    }


}

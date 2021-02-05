package com.madao.exception;

import com.madao.exception.custom.CaptchaNotMatchException;
import com.madao.exception.custom.PhoneExistingException;
import com.madao.exception.custom.ResourceNotFoundException;
import com.madao.exception.custom.UserException;
import com.madao.utils.JsonData;
import com.madao.utils.LogBack;
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

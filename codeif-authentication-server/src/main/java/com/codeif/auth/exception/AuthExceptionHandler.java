package com.codeif.auth.exception;

import com.codeif.enums.StatusEnum;
import com.codeif.utils.JsonData;
import com.codeif.utils.LogBack;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一系统异常处理类
 **/
@RestControllerAdvice
public class AuthExceptionHandler {

    /**
     * ValidateCodeException
     *
     * @param ex ValidateCodeException
     */
    @ExceptionHandler(ValidateCodeException.class)
    public JsonData<Void> validateCodeException(ValidateCodeException ex) {
        LogBack.error(ex.getMessage(), ex);
        return JsonData.failed(StatusEnum.PARAM_ILLEGAL);
    }

}

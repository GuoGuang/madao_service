package com.codeway.auth.handler;

import com.codeway.enums.StatusEnum;
import com.codeway.utils.JsonData;
import com.codeway.utils.LogBack;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * 登录发生错误时执行
 **/

@Component
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {

	@Override
	public ResponseEntity translate(Exception e) throws Exception {
        LogBack.error("登录发生错误时异常----------->{}",e.getMessage(), e);
        JsonData<Void> jsonData = JsonData.failed(StatusEnum.SYSTEM_ERROR);
        if (e instanceof InternalAuthenticationServiceException) {
            jsonData = JsonData.failed(StatusEnum.SYSTEM_ERROR);
        } else if (e instanceof InvalidGrantException) {
            jsonData = JsonData.failed(StatusEnum.LOGIN_ERROR);
        }
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }
}
package com.madao.auth.handler;

import com.madao.enums.StatusEnum;
import com.madao.utils.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * 登录发生错误时执行
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Component
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    @Override
    public ResponseEntity translate(Exception e) {
        log.error("登录发生错误时异常----------->{}", e.getMessage(), e);
        JsonData<Void> jsonData = JsonData.failed(StatusEnum.SYSTEM_ERROR, e.getMessage());
        if (e instanceof InternalAuthenticationServiceException) {
            jsonData = JsonData.failed(StatusEnum.SYSTEM_ERROR);
        } else if (e instanceof InvalidGrantException) {
            jsonData = JsonData.failed(StatusEnum.LOGIN_ERROR);
        }
        return new ResponseEntity<>(jsonData, HttpStatus.OK);
    }
}
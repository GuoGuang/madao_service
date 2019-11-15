package com.ibole.auth.handler;

import com.ibole.enums.StatusEnum;
import com.ibole.utils.JsonData;
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
		JsonData jsonData = new JsonData(false, StatusEnum.SYSTEM_ERROR.getCode(), StatusEnum.SYSTEM_ERROR.getMsg());
		if (e instanceof InternalAuthenticationServiceException){
			jsonData = new JsonData(false, StatusEnum.SYSTEM_ERROR.getCode(), StatusEnum.SYSTEM_ERROR.getMsg());
		}else if (e instanceof InvalidGrantException){
			jsonData = new JsonData(false, StatusEnum.LOGIN_ERROR.getCode(), StatusEnum.LOGIN_ERROR.getMsg());
		}
		return new ResponseEntity<>(jsonData,HttpStatus.OK);
	}
}
package com.codeway.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义异常：校验验证码异常
 **/
public class ValidateCodeException extends AuthenticationException {

	public ValidateCodeException(String msg) {
		super(msg);
	}

}

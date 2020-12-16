package com.madao.auth.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthException extends AuthenticationException {

	public AuthException(String msg) {
		super(msg);
	}

}

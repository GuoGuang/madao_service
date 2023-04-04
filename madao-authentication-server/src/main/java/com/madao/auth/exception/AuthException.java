package com.madao.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class AuthException extends AuthenticationException {

	public AuthException(String msg) {
		super(msg);
	}

}

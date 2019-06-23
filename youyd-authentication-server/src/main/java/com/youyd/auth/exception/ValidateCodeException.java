/**
 * 
 */
package com.youyd.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义异常：校验验证码异常
 * @author : LGG
 * @create : 2019-06-18 14:34
 **/
public class ValidateCodeException extends AuthenticationException {

	public ValidateCodeException(String msg) {
		super(msg);
	}

}

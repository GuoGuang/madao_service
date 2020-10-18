package com.codeway.exception.custom;

public class CaptchaExpiredException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CaptchaExpiredException() {
		super("验证码过期！");
	}

}

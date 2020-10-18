package com.codeway.exception.custom;

public class CaptchaNotMatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CaptchaNotMatchException() {
		super("验证码不匹配！");
	}

}

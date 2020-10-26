package com.codeway.exception.custom;

import com.codeway.enums.StatusEnum;

public class CaptchaNotMatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CaptchaNotMatchException() {
		super(StatusEnum.CAPTCHA_NOT_MATCH.getMsg());
	}

}

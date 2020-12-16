package com.madaoo.exception.custom;

import com.madaoo.enums.StatusEnum;

public class CaptchaNotMatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CaptchaNotMatchException() {
		super(StatusEnum.CAPTCHA_NOT_MATCH.getMsg());
	}

}

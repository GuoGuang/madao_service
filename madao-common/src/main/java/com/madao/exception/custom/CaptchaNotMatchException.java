package com.madao.exception.custom;

import com.madao.enums.StatusEnum;

public class CaptchaNotMatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CaptchaNotMatchException() {
		super(StatusEnum.CAPTCHA_NOT_MATCH.getMsg());
	}

}

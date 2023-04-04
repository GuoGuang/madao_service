package com.madao.exception.custom;

import com.madao.enums.StatusEnum;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class CaptchaNotMatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CaptchaNotMatchException() {
		super(StatusEnum.CAPTCHA_NOT_MATCH.getMsg());
	}

}

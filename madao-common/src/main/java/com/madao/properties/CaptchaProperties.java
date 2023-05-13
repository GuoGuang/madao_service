package com.madao.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 图片验证码配置项
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Getter
@Setter
public class CaptchaProperties extends SmsCodeProperties {

	private int width = 124;
	private int height = 30;
	public CaptchaProperties() {
		setLength(4);
	}

}

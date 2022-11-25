package com.madao.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 验证码配置
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Getter
@Setter
public class ValidateCodeProperties {

	/**
	 * 图片验证码配置
	 */
	private CaptchaProperties image = new CaptchaProperties();
	/**
	 * 短信验证码配置
	 */
	private SmsCodeProperties sms = new SmsCodeProperties();

}

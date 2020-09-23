package com.codeway.auth.validate.impl.sms;

import com.codeway.utils.LogBack;

/**
 * 默认的短信验证码发送器
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

	@Override
	public void send(String phone, String code) {
		LogBack.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
		LogBack.info("向手机 {} 发送短信验证码: {}",phone,code);
	}

}

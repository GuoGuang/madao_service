package com.madaoo.auth.validate.impl.sms;

import com.madaoo.utils.LogBack;

/**
 * 默认的短信验证码发送器
 */
public class DefaultSmsCodeSender implements com.madaoo.auth.validate.impl.sms.SmsCodeSender {

	@Override
	public void send(String phone, String code) {
		LogBack.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
		LogBack.info("向手机 {} 发送短信验证码: {}", phone, code);
	}

}

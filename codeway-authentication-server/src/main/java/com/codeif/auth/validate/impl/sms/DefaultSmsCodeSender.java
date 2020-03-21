/**
 * 
 */
package com.codeif.auth.validate.impl.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认的短信验证码发送器
 * 
 * @author zhailiang
 *
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void send(String phone, String code) {
		logger.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
		logger.info("向手机"+phone+"发送短信验证码"+code);
	}

}

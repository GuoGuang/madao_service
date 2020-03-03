package com.codeif.auth.validate.impl.sms;

/**
 * @author zhailiang
 *
 */
public interface SmsCodeSender {
	
	/**
	 * @param phone
	 * @param code
	 */
	void send(String phone, String code);

}

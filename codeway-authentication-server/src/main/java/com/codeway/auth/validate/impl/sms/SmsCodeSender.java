package com.madaoo.auth.validate.impl.sms;

public interface SmsCodeSender {
	
	/**
	 * @param phone
	 * @param code
	 */
	void send(String phone, String code);

}

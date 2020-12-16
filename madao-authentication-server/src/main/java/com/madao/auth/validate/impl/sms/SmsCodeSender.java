package com.madao.auth.validate.impl.sms;

public interface SmsCodeSender {
	
	/**
	 * @param phone
	 * @param code
	 */
	void send(String phone, String code);

}

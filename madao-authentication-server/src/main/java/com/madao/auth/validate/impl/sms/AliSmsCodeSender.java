package com.madao.auth.validate.impl.sms;

import com.madao.utils.LogBack;
import com.madao.utils.third.Smsbao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 阿里验证码发送器
 */
public class AliSmsCodeSender implements SmsCodeSender {

	// @Autowired
	//RabbitUtil rabbitUtil;

	@Autowired(required = false)
	private Smsbao smsbao;

	@Override
	public void send(String phone, String code) {
		smsbao.sendSms(phone, code);
        LogBack.info("向手机" + phone + "发送短信验证码" + code);
//		rabbitUtil.sendMessageToExchange(,"");
    }

}

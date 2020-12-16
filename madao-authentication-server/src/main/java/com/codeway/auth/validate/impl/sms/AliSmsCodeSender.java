package com.madaoo.auth.validate.impl.sms;

import com.madaoo.utils.LogBack;
import com.madaoo.utils.third.Smsbao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 阿里验证码发送器
 */
public class AliSmsCodeSender implements com.madaoo.auth.validate.impl.sms.SmsCodeSender {

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

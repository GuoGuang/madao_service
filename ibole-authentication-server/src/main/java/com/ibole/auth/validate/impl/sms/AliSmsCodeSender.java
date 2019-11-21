package com.ibole.auth.validate.impl.sms;

import com.ibole.amqp.RabbitUtil;
import com.ibole.utils.LogBack;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class AliSmsCodeSender implements SmsCodeSender {

    @Autowired
    RabbitUtil rabbitUtil;

    @Override
    public void send(String phone, String code) {
        LogBack.info("向手机" + phone + "发送短信验证码" + code);
//		rabbitUtil.sendMessageToExchange(,"");
    }

}

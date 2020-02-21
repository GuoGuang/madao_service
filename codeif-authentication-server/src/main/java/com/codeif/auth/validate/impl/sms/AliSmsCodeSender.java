package com.codeif.auth.validate.impl.sms;

import com.codeif.utils.LogBack;

/**
 *
 */
public class AliSmsCodeSender implements SmsCodeSender {

   // @Autowired
    //RabbitUtil rabbitUtil;

    @Override
    public void send(String phone, String code) {
        LogBack.info("向手机" + phone + "发送短信验证码" + code);
//		rabbitUtil.sendMessageToExchange(,"");
    }

}

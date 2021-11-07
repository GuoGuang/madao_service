package com.madao.auth.validate.impl.sms;

import com.madao.utils.third.Smsbao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 阿里验证码发送器
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
public class AliSmsCodeSender implements SmsCodeSender {

    // @Autowired
    //RabbitUtil rabbitUtil;

    @Autowired(required = false)
    private Smsbao smsbao;

    @Override
    public void send(String phone, String code) {
        smsbao.sendSms(phone, code);
        log.info("向手机" + phone + "发送短信验证码" + code);
//		rabbitUtil.sendMessageToExchange(,"");
    }

}

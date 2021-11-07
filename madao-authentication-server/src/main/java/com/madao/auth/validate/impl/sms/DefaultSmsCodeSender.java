package com.madao.auth.validate.impl.sms;


import lombok.extern.slf4j.Slf4j;

/**
 * 默认的短信验证码发送器
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String phone, String code) {
        log.warn("请配置真实的短信验证码发送器(SmsCodeSender)");
        log.info("向手机 {} 发送短信验证码: {}", phone, code);
    }

}

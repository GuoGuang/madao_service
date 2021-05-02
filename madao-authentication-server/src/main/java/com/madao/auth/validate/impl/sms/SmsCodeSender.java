package com.madao.auth.validate.impl.sms;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public interface SmsCodeSender {

    /**
     * @param phone
     * @param code
     */
    void send(String phone, String code);

}

package com.madao.user.listener;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.madao.utils.third.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 监听MQ接口, 发送短信
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Component
@ConditionalOnProperty(value = "aliyun.sms")
public class SmsListener {

    @Autowired(required = false)
    private SmsUtil smsUtil;

    @Value("${aliyun.sms.template_code}")
    private String templateCode;// 模板编号
    @Value("${aliyun.sms.sign_name}")
    private String signName;// 签名 【阿里云】

    /**
     * 发送短信
     *
     * @param message 监听到的数据
     */
    @RabbitHandler
    public SendSmsResponse sendSms(Map<String, String> message) {
        log.info("手机号：{}，验证码：{}；", message.get("mobile"), message.get("code"));
        try {
            return smsUtil.sendSms(message.get("mobile"), templateCode, signName,
                    "{\"code\":\"" + message.get("code") + "\"}");
        } catch (ClientException | NullPointerException e) {
            log.error("发送手机验证码失败：{}", e.getMessage(), e);
        }

        return null;
    }

}

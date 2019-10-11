package com.youyd.user.listener;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.youyd.utils.third.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description 监听MQ接口, 发送短信
 * @author LGG
 * @create 2018-10-21 19:36
 **/

@Component
//@RabbitListener(queues = "sms")
@ConditionalOnProperty(value = "aliyun.sms")
public class SmsListener {

	@Autowired
	private SmsUtil smsUtil;

	@Value("${aliyun.sms.template_code}")
	private String template_code;// 模板编号
	@Value("${aliyun.sms.sign_name}")
	private String sign_name;// 签名 【阿里云】

	/**
	 * 发送短信
	 * @param message 监听到的数据
	 */
	@RabbitHandler
	public SendSmsResponse sendSms(Map<String,String> message){
		System.out.println("手机号："+message.get("mobile"));
		System.out.println("验证码："+message.get("code"));
		try {
			return smsUtil.sendSms(message.get("mobile"), template_code, sign_name,
					"{\"code\":\"" + message.get("code") + "\"}");
		} catch (ClientException e) {
			e.printStackTrace();
		}

return null;
	}

}

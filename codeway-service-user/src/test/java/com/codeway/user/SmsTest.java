package com.codeway.user;

import com.codeway.utils.LogBack;
import com.codeway.utils.third.SmsUtil;
import com.codeway.utils.third.Smsbao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsTest {

	@Autowired(required = false)
	private SmsUtil smsUtil;
	@Autowired
	private Smsbao smsbao;

	//@Value("${aliyun.sms.template_code}")
	//private String template_code;// 模板编号
	//@Value("${aliyun.sms.sign_name}")
	//private String sign_name;// 签名 【阿里云】

	/**
	 * 发送短信
	 */
	@Test
	public void sendSms(){
		try {
//			 smsUtil.sendSms("18901607027",
//					  "SMS_168587174",
//					 	 "xx",
//							"{'code':'66666'}");
			String phone = "13105648403";
			smsbao.sendSms(phone, "123");
		} catch (Exception e) {
			LogBack.error(e.getMessage(), e);
		}

	}

}

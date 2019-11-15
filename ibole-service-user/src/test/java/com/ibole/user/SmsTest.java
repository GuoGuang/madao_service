package com.ibole.user;

import com.aliyuncs.exceptions.ClientException;
import com.ibole.utils.third.SmsUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsTest {

	@Autowired
	private SmsUtil smsUtil;

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
			 smsUtil.sendSms("15866534150", "SMS_168587174", "探索云",
					"{'code':'66666'}");
		} catch (ClientException e) {
			e.printStackTrace();
		}

	}
}

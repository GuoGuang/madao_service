package com.youyd.sms.listener;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsListenerTest {

	@Autowired
	private SmsListener smsListener;

	@Test
	public void sendSms() {
		HashMap<String, String> map = new HashMap<>();
		map.put("mobile","17667198751");
		map.put("code","666666");
		SendSmsResponse sendSmsResponse = smsListener.sendSms(map);
		System.out.println("6666:"+sendSmsResponse.getCode());
	}
}
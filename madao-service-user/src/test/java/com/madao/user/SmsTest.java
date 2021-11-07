package com.madao.user;

import com.madao.utils.third.SmsUtil;
import com.madao.utils.third.Smsbao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
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
    public void sendSms() {
        try {
//			 smsUtil.sendSms("18901607027",
//					  "SMS_168587174",
//					 	 "xx",
//							"{'code':'66666'}");
            String phone = "13105648403";
            String result = smsbao.sendSms(phone, "123");
            assertThat(result).isNotNull();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

}

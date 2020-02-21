/*
package com.codeif.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
com.codeif.redis.RedisService
import com.codeif.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

*/
/**
 * 测试mybatis-plus
 *//*

@RunWith(SpringRunner.class)
@SpringBootTest
public class JacksonTest {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RedisService redisService;


	@Test
	public void testPlusaa(){
		ValidateCode validateCode = new ValidateCode("1", 12);
		redisService.setKeyStr("aaa", JsonUtil.toJsonString(validateCode),11111L);
	}

	@Test
	public void testPlus() throws JsonProcessingException {
		String s = objectMapper.writeValueAsString("{aa:1}");
		System.out.println(s);
	}

	@Test
	public void testLocalDateTime() throws IOException {

		Object aaa = redisService.getKeyStr("aaa");
		ValidateCode s = objectMapper.readValue(aaa.toString(),ValidateCode.class);

		System.out.println(s);
	}
}*/

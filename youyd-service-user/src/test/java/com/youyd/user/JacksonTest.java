package com.youyd.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试mybatis-plus
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JacksonTest {

	@Autowired
	private static ObjectMapper objectMapper;
	@Test
	public void testPlus() throws JsonProcessingException {
		String s = objectMapper.writeValueAsString("{aa:1}");
		System.out.println(s);
	}
}
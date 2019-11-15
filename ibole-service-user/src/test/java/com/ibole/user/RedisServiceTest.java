package com.ibole.user;

import com.ibole.cache.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {
	@Autowired
	private RedisService redisService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Test
	public void testPlus(){
		String key = "user_token:6666";
		redisService.setKeyStr(key,"{}",1111L);

		Object hget = redisService.getKeyStr(key);
		System.out.println(hget);
	}



	@Test
	public void testLocalDateTime() throws IOException {
		String encode = passwordEncoder.encode("123456");
		boolean matches = passwordEncoder.matches("$2a$10$fJzQj58QQrNFCbE7xGVULO/Mg6ziERy3MyoIUVdeOwetSO1juQORC", "$2a1$10$BhsnYeg/UmJx3SccI7jI0eMnMO7SMiU9nXofC8xkb3UcjtMd1h8tS");
		boolean matches1 = passwordEncoder.matches("123456", "$2a$10$EPTw4OF4EHoHtWA.UjoDS.hZsjaOylEF.bBxf2YHST7VjpWr6.kd.");
		System.out.println(encode);
		System.out.println(matches);
		System.out.println(matches1);
	}
}

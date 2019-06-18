package com.youyd.user;

import com.youyd.cache.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {
	@Autowired
	private RedisService redisService;

	@Test
	public void testPlus(){
		String key = "user_token:6666";
		redisService.setKeyStr(key,"{}",1111L);

		Object hget = redisService.getKeyStr(key);
		System.out.println(hget);
	}

}

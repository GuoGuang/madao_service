package com.ibole.article;

import com.ibole.db.redis.service.RedisService;
import com.ibole.db.redis.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaArticleServiceTest {

	@Autowired
	private RedisService redisService;

	@Test
	public void test1(){

		//Article articleRedis = redisService.get(RedisConstant.REDIS_KEY_ARTICLE + 1125034705563664386L);
		//System.out.println(articleRedis);
	}

}
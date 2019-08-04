package com.youyd;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Feign 请求日志
 * @author : LGG
 * @create : 2019-06-11
 **/
@Configuration
public class FeignClientConfig {
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
}
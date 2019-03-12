package com.youyd.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * https://blog.csdn.net/kobejayandy/article/details/45869861
 */
@Configuration
public class JacksonConfig {

	@Bean
	@Primary
	@ConditionalOnMissingBean(ObjectMapper.class)
	public ObjectMapper jacksonObjectMapper(){
		ObjectMapper mapper = new ObjectMapper();
		// （禁止SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS）
		// 解决 No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistL
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

		return mapper;
	}

}

package com.youyd.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @description: 配置SpringDataRedis
 * @author: LGG
 * @create: 2018-12-25
 *      RedisSerializer   redis序列化的接口类
 *      OxmSerializer         xml到object的序列化/反序列化
 *      StringRedisSerializer     string字符串的序列化/反序列化
 *      JacksonJsonRedisSerializer     json到object的序列化/反序列化
 *      Jackson2JsonRedisSerializer     json到object的序列化/反序列化
 *      JdkSerializationRedisSerializer     java对象的序列化/反序列化
 **/

@Configuration
//@PropertySource("classpath:properties/redis.properties")
public class RedisConfig {

	/**
	 * 配置自定义Json序列化器 jackson2
	 * @param redisConnectionFactory ：redis连接工厂
	 * @return ：RedisTemplate
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(redisConnectionFactory);
	    // 使用jackson2序列化
	    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

	    ObjectMapper om = new ObjectMapper();
	    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	    jackson2JsonRedisSerializer.setObjectMapper(om);
	    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

	    // key采用String的序列化方式
	    template.setKeySerializer(stringRedisSerializer);
	    // hash的key也采用String的序列化方式
	    template.setHashKeySerializer(stringRedisSerializer);
	    // value序列化方式采用jackson
	    template.setValueSerializer(jackson2JsonRedisSerializer);

	    // hash的value序列化方式采用jackson
	    template.setHashValueSerializer(jackson2JsonRedisSerializer);
	    template.afterPropertiesSet();

		// 设置默认使用Jackson序列化
		template.setDefaultSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
	    return template;
	}


}


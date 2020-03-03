/*
package com.codeif.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

*/
/**
 * @program: SpringBoot
 * 配置自定义Json序列化器
 * @author LinGuoGuang
 **//*

@Configuration
public class JsonRedisSerializable {

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        // 设置默认使用Jackson序列化
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return template;
    }
}
*/

package com.youyd.spit.config;

import org.springframework.context.annotation.Configuration;

/**
 * @program: SpringBoot
 * @description: 配置自定义Json序列化器
 * @author: LinGuoGuang
 **/
//@Configuration
public class JsonRedisSerializable {

/*    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        // 设置默认使用Jackson序列化
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return template;
    }*/
}

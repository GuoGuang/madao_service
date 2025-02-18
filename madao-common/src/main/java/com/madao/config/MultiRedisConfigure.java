//package com.madao.config;
//
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisPassword;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.time.Duration;
//
///**
// * 切换多Redis数据源
// */
//@Configuration
//public class MultiRedisConfigure {
//
//    /**
//     * 业务数据源
//     */
//    @Bean
//    public RedisTemplate businessRedisTemplate(RedisConfig redisConfig) {
//        long timeout = redisConfig.getTimeout();
//        int maxActive = redisConfig.getMaxActive();
//        int maxWait = redisConfig.getMaxWait();
//        int maxIdle = redisConfig.getMaxIdle();
//        int minIdle = redisConfig.getMinIdle();
//
//        RedisStandaloneConfiguration standaloneConfiguration = redisConfig.getStandaloneConfiguration();
//        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
//        genericObjectPoolConfig.setMaxTotal(maxActive);
//        genericObjectPoolConfig.setMaxWaitMillis(maxWait);
//        genericObjectPoolConfig.setMaxIdle(maxIdle);
//        genericObjectPoolConfig.setMinIdle(minIdle);
//
//        LettuceConnectionFactory connectionFactory = lettuceConnectionFactory(standaloneConfiguration, genericObjectPoolConfig, timeout);
//        connectionFactory.afterPropertiesSet();
//        return createRedisTemplate(connectionFactory);
//    }
//
//    private LettuceConnectionFactory lettuceConnectionFactory(RedisStandaloneConfiguration standaloneConfiguration, GenericObjectPoolConfig genericObjectPoolConfig, long timeout) {
//        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder = LettucePoolingClientConfiguration.builder();
//        builder.poolConfig(genericObjectPoolConfig);
//        builder.commandTimeout(Duration.ofSeconds(timeout));
//        return new LettuceConnectionFactory(standaloneConfiguration, builder.build());
//    }
//
//    /**
//     * 默认Redis数据源，支持供BladeRedis使用
//     */
//    @Bean
//    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        return createRedisTemplate(redisConnectionFactory);
//    }
//
//    /**
//     * 根据不同数据源配置入参，创建RedisTemplate
//     */
//    private RedisTemplate createRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setKeySerializer(stringRedisSerializer);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
//    @Bean
//    public RedisConfig businessRedisConfiguration(
//            @Value("${spring.business-redis.database}") int database,
//            @Value("${spring.business-redis.password}") String password,
//            @Value("${spring.business-redis.host}") String host,
//            @Value("${spring.business-redis.port}") int port,
//            @Value("${spring.business-redis.timeout}") long timeout,
//            @Value("${spring.business-redis.lettuce.pool.max-active}") int maxActive,
//            @Value("${spring.business-redis.lettuce.pool.max-wait}") int maxWait,
//            @Value("${spring.business-redis.lettuce.pool.max-idle}") int maxIdle,
//            @Value("${spring.business-redis.lettuce.pool.min-idle}") int minIdle) {
//
//        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
//        standaloneConfiguration.setDatabase(database);
//        standaloneConfiguration.setHostName(host);
//        standaloneConfiguration.setPort(port);
//
//        if (StringUtils.isNotEmpty(password)) {
//            RedisPassword redisPassword = RedisPassword.of(password);
//            standaloneConfiguration.setPassword(redisPassword);
//        }
//
//        return new RedisConfig(standaloneConfiguration, timeout, maxActive, maxWait, maxIdle, minIdle);
//    }
//}
//
//@Getter
//@AllArgsConstructor
//class RedisConfig {
//    private final RedisStandaloneConfiguration standaloneConfiguration;
//    private final long timeout;
//    private final int maxActive;
//    private final int maxWait;
//    private final int maxIdle;
//    private final int minIdle;
//}
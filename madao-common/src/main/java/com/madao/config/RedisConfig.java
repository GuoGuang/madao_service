package com.madao.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;

import java.time.Duration;

/**
 * //@PropertySource("classpath:properties/redis.properties")
 * 配置SpringDataRedis
 * RedisSerializer   redis序列化的接口类
 * OxmSerializer         xml到object的序列化/反序列化
 * StringRedisSerializer     string字符串的序列化/反序列化
 * JacksonJsonRedisSerializer     json到object的序列化/反序列化
 * Jackson2JsonRedisSerializer     json到object的序列化/反序列化
 * JdkSerializationRedisSerializer     java对象的序列化/反序列化
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Configuration
public class RedisConfig {

	/**
	 * 配置自定义Json序列化器 jackson2
	 *
	 * @param redisConnectionFactory ：redis连接工厂
	 * @return ：RedisTemplate
	 */
	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		template.setKeySerializer(stringRedisSerializer);
		template.setHashKeySerializer(stringRedisSerializer);
		template.setValueSerializer(genericJackson2JsonRedisSerializer);
		template.setHashValueSerializer(genericJackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}

	/**
	 * 配置缓存管理器
	 *
	 * @param factory Redis 线程安全连接工厂
	 * @return 缓存管理器
	 */

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {

		RedisCacheConfiguration userCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				// 设置过期时间 10 分钟
				.entryTtl(Duration.ofMinutes(10))
				// 设置缓存前缀
				.prefixCacheNameWith("cache:user:")
				// 禁止缓存 null 值
				.disableCachingNullValues()
				// 设置 key 序列化
				.serializeKeysWith(keyPair())
				// 设置 value 序列化
				.serializeValuesWith(valuePair());
		RedisCacheConfiguration articleCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(30))
				.prefixCacheNameWith("cache:article:")
				.disableCachingNullValues()
				.serializeKeysWith(keyPair())
				.serializeValuesWith(valuePair());
		return RedisCacheManager.builder(factory)
				.withCacheConfiguration("user", userCacheConfig)
				.withCacheConfiguration("article", articleCacheConfig)
				.build();
	}

	/**
	 * 配置键序列化
	 *
	 * @return StringRedisSerializer
	 */
	private RedisSerializationContext.SerializationPair<String> keyPair() {
		return RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());
	}

	/**
	 * 配置值序列化
	 */
	private RedisSerializationContext.SerializationPair<Object> valuePair() {
		return RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());
	}

	/**
	 * 配置Redis+Lua脚本，供接口限流注解使用
	 */
	@Bean
	public DefaultRedisScript<Long> rateLimitScript() {
		DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
		redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/limit.lua")));
		redisScript.setResultType(Long.class);
		return redisScript;
	}
}


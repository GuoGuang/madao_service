package com.madao.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
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
		// 使用jackson2序列化
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		mapper.registerModule(new Jdk8Module())
				.registerModule(new JavaTimeModule());
		jackson2JsonRedisSerializer.setObjectMapper(mapper);
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

	/**
	 * 配置缓存管理器
	 * @param factory Redis 线程安全连接工厂
	 * @return 缓存管理器
	 */
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		RedisCacheConfiguration userCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				// 设置过期时间 10 分钟
				.entryTtl(Duration.ofMinutes(10))
				// 设置缓存前缀
				.prefixCacheNameWith("cache:article:")
				// 禁止缓存 null 值
				.disableCachingNullValues()
				// 设置 key 序列化
				.serializeKeysWith(keyPair())
				// 设置 value 序列化
				.serializeValuesWith(valuePair());
		RedisCacheConfiguration articleCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(30))
				.prefixCacheNameWith("cache:user:")
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
	 * @return StringRedisSerializer
	 */
	private RedisSerializationContext.SerializationPair<String> keyPair() {
		return RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());
	}

	/**
	 * 配置值序列化，使用 GenericJackson2JsonRedisSerializer 替换默认序列化
	 * @return GenericJackson2JsonRedisSerializer
	 */
	private RedisSerializationContext.SerializationPair<Object> valuePair() {
		return RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
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


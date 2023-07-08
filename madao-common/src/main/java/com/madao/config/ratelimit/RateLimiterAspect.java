package com.madao.config.ratelimit;

import com.madao.exception.custom.RateLimiterException;
import com.madao.utils.IPUtils;
import com.madao.utils.security.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * 接口限流，基于LUA脚本，时间窗口算法限流
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022-05-22 14:36
 */
@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class RateLimiterAspect {

	private final RedisTemplate<Object, Object> redisTemplate;

	private final RedisScript<Long> limitScript;

	@Before("@annotation(rateLimiter)")
	public void doBefore(JoinPoint point, RateLimiter rateLimiter) {
		String key = rateLimiter.key();
		int time = rateLimiter.time();
		int count = rateLimiter.count();

		String combineKey = getCombineKey(rateLimiter, point);
		List<Object> keys = Collections.singletonList(combineKey);
		try {
			Long number = redisTemplate.execute(limitScript, keys, count, time);
			if (number == null || number.intValue() > count) {
				log.error("限流请求'{}',当前请求'{}',当前缓存key'{}'已达峰值！", count, number.intValue(), key);
				throw new RateLimiterException();
			}
			log.info("限流请求'{}',当前请求'{}',缓存key'{}'", count, number.intValue(), key);
		} catch (Exception e) {
			throw new RateLimiterException(e.getMessage());
		}
	}

	/**
	 * 组合key：rate_limit:ip地址-包名-方法名
	 */
	public String getCombineKey(RateLimiter rateLimiter, JoinPoint point) {
		StringBuilder sb = new StringBuilder(rateLimiter.key());
		if (rateLimiter.limitType() == LimitType.IP) {
			sb.append(IPUtils.getIpAddr(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest())).append("-");
		} else if (rateLimiter.limitType() == LimitType.USER_ID) {
			sb.append(SecurityUtils.getCurrentUserId()).append("-");
		}
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		Class<?> targetClass = method.getDeclaringClass();
		sb.append(targetClass.getName()).append("-").append(method.getName());
		return sb.toString();
	}
}
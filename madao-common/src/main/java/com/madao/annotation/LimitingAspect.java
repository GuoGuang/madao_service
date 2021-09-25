package com.madao.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Aspect
@Component
public class LimitingAspect {

	@Autowired
	private RedisTemplate<String, Object>  redisTemplate;

	@Pointcut(value = "@annotation(com.madao.annotation.Limiting)")
	public void controllerAspect() {
		log.info("切入点签名");
	}

	@Around("controllerAspect()")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("开始--");
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Limiting limiting = method.getAnnotation(Limiting.class);
		Object[] args = joinPoint.getArgs();
		String o2oCode = args[0].toString();

		int maxCount = limiting.maxCount();
		TimeUnit unit = limiting.unit();
		String point = limiting.point();
		long timeout = limiting.timeout();
		AtomicInteger count = new AtomicInteger((Integer)Optional.ofNullable(redisTemplate.boundValueOps(point).get()).orElse(0));
		if (count.get() >= maxCount) {
			String msg = "当前已达三方接口调用上限，请稍后再试！";
			log.error(msg);
			throw new RuntimeException(msg);
		}

		try {
			joinPoint.proceed();
		} catch (Throwable throwable) {
			log.info("异常--");
			throw throwable;
		}finally {
			redisTemplate.boundValueOps(point).set(count.incrementAndGet(),timeout,unit);
		}

		log.info("结束--");
	}

}
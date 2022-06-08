package com.madao.config.ratelimit;

import java.lang.annotation.*;

/**
 * 接口限流注解
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2022-05-22 15:21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
	/**
	 * 限流key：rate_limit:ip地址-包名-方法名
	 */
	String key() default "rate_limit:";

	/**
	 * 限流时间,单位秒
	 */
	int time() default 60;

	/**
	 * 限流次数
	 */
	int count() default 100;

	/**
	 * 限流类型
	 */
	LimitType limitType() default LimitType.DEFAULT;
}

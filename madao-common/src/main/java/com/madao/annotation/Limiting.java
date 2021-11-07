package com.madao.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 对第三方接口调用频率进行限流
 * note: 该注解定义的方法内不能包含循环体，可以将当前注解定义的方法抽出，由上层方法循环调用此方法
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Limiting {

	int maxCount() default 0;
	TimeUnit unit() default TimeUnit.SECONDS;
	long timeout() default 0;
	String point() default "";

}

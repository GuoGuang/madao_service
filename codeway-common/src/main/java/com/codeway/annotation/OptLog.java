package com.codeway.annotation;


import java.lang.annotation.*;

/**
 * 操作日志切面
 **/

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptLog {

	/**
	 * 要执行的操作类型比如：add操作
	 * {@link com.codeway.constant.CommonConst}
	 */
	int operationType() default 0;

	/**
	 * 要执行的具体操作比如：添加用户
	 * {@link com.codeway.constant.CommonConst}
	 */
	String operationName() default "";
}

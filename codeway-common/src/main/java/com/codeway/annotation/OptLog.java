package com.madaoo.annotation;


import com.madaoo.enums.OptLogType;

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
	 * {@link com.madaoo.constant.CommonConst}
	 */
	OptLogType operationType();

	/**
	 * 要执行的具体操作比如：添加用户
	 * {@link com.madaoo.constant.CommonConst}
	 */
	String operationName() default "";
}

package com.madao.annotation;


import com.madao.enums.OptLogType;

import java.lang.annotation.*;

/**
 * 操作日志切面
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptLog {

    /**
     * 要执行的操作类型比如：add操作
     * {@link com.madao.constant.CommonConst}
     */
    OptLogType operationType();

    /**
     * 要执行的具体操作比如：添加用户
     * {@link com.madao.constant.CommonConst}
     */
    String operationName() default "";
}

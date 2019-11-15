package com.ibole.annotation;

import com.ibole.constraints.CronValidator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * CRON表达式验证注解
 * @author : LGG
 * @create : 2019年05-28
 **/
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CronValidator.class)
@Documented
public @interface CronExpress {

	String message() default "";

	Class<?>[] groups() default {};


}

package com.madao.annotation;

import cn.hutool.core.util.IdcardUtil;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * china idCard verification
 * <p>
 * Supported types are:
 * <ul>
 *     <li>{@code String}</li>
 * </ul>
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2021-09-02
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CheckIdCard.ValidatorValue.class)
public @interface CheckIdCard {

	int value() default 0;

	String message() default "illegal idCard";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * Defines several {@link CheckIdCard} annotations on the same element
	 */
	@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		CheckIdCard[] value();
	}

	class ValidatorValue implements ConstraintValidator<CheckIdCard, String> {

		@Override
		public void initialize(CheckIdCard constraintAnnotation) {
		}

		@Override
		public boolean isValid(String content, ConstraintValidatorContext constraintValidatorContext) {
			if (content == null) {
				return false;
			}
			return IdcardUtil.isValidCard(content);
		}
	}

}

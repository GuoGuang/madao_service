package com.madao.annotation;

import cn.hutool.core.lang.Validator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * mailbox verification
 *
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
@Constraint(validatedBy = CheckEmail.ValidatorValue.class)
public @interface CheckEmail {

	int value() default 0;

	String message() default "invalid email";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * Defines several {@link CheckEmail} annotations on the same element
	 */
	@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		CheckEmail[] value();
	}

	class ValidatorValue implements ConstraintValidator<CheckEmail, String> {

		@Override
		public void initialize(CheckEmail constraintAnnotation) {
		}

		@Override
		public boolean isValid(String content, ConstraintValidatorContext constraintValidatorContext) {
			if (content == null) {
				return false;
			}
			return Validator.isEmail(content);
		}
	}

}

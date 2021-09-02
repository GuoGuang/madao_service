package com.madao.annotation;

import cn.hutool.core.lang.Validator;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * china mobile  verification
 *
 * <p>
 * Supported types are:
 * <ul>
 *     <li>{@code Date}</li>
 *     <li>{@code LocalDate}</li>
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
@Constraint(validatedBy = {CheckDate.CheckDateValidatorForDate.class,CheckDate.CheckDateValidatorForLocalDate.class})
public @interface CheckDate {

	int value() default 0;

	String message() default "invalid date";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * Defines several {@link CheckDate} annotations on the same element
	 */
	@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		CheckDate[] value();
	}

	class CheckDateValidatorForDate implements ConstraintValidator<CheckDate, Date> {

		@Override
		public void initialize(CheckDate constraintAnnotation) {}

		@Override
		public boolean isValid(Date content, ConstraintValidatorContext constraintValidatorContext) {
			if (content == null || "1970-01-01".equals(content.toString())) {
				return false;
			}
			String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
			return Validator.isMatchRegex(regex,content.toString());
		}
	}

	class CheckDateValidatorForLocalDate implements ConstraintValidator<CheckDate, LocalDate> {

		@Override
		public void initialize(CheckDate constraintAnnotation) {}

		@Override
		public boolean isValid(LocalDate content, ConstraintValidatorContext constraintValidatorContext) {
			if (content == null || "1970-01-01".equals(content.toString())) {
				return false;
			}
			String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
			return Validator.isMatchRegex(regex,content.toString());
		}
	}

}

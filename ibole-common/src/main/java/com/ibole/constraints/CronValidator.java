package com.ibole.constraints;

import com.ibole.annotation.CronExpress;
import org.quartz.CronExpression;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义CRON表达式限制条件
 * @author : LGG
 * @create : 2019年05-28
 **/
public class CronValidator implements ConstraintValidator<CronExpress, String> {

	@Override
	public void initialize(CronExpress constraintAnnotation) {
		// Do nothing
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean validExpression = CronExpression.isValidExpression(value);
		return !validExpression;
	}
}
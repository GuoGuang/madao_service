package com.madaoo.auth.validate;

import com.madaoo.auth.exception.AuthException;
import com.madaoo.enums.ValidateCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 校验码处理器管理类
 * 校验码处理器，封装不同校验码的处理逻辑
 * 使用Spring中常用的 策略模式+依赖搜索 实现
 **/
@Component
public class ValidateCodeProcessorHolder {

	@Autowired
	private Map<String, com.madaoo.auth.validate.ValidateCodeProcessor> validateCodeProcessors;

	/**
	 * 根据验证码类型获取对应的处理器
	 *
	 * @param type
	 * @return
	 */
	public com.madaoo.auth.validate.ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
		return findValidateCodeProcessor(type.toString().toLowerCase());
	}

	/**
	 * 获取type类型的校验码处理器
	 */
	public com.madaoo.auth.validate.ValidateCodeProcessor findValidateCodeProcessor(String type) {
		String name = type.toLowerCase() + com.madaoo.auth.validate.ValidateCodeProcessor.class.getSimpleName();
		com.madaoo.auth.validate.ValidateCodeProcessor processor = validateCodeProcessors.get(name);
		if (processor == null) {
			throw new AuthException("验证码处理器" + name + "不存在");
		}
		return processor;
	}

}

package com.madao.auth.validate.impl;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码生成器
 **/
public interface ValidateCodeGenerator {

	/**
	 * 生成校验码
	 *
	 * @param request ServletWebRequest
	 * @return ValidateCode
	 */
	com.madao.auth.validate.impl.ValidateCode generate(ServletWebRequest request);
	
}

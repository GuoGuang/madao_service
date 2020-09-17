package com.codeway.auth.validate.impl;

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
	ValidateCode generate(ServletWebRequest request);
	
}

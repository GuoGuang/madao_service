package com.ibole.auth.validate.impl;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码生成器
 * @author : LGG
 * @create : 2019-06-18 14:34
 **/
public interface ValidateCodeGenerator {

	/**
	 * 生成校验码
	 * @param request
	 * @return
	 */
	ValidateCode generate(ServletWebRequest request);
	
}

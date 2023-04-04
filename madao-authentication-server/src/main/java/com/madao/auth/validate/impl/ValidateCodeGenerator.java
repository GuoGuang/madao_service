package com.madao.auth.validate.impl;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码生成器
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public interface ValidateCodeGenerator {

	/**
	 * 生成校验码
	 *
	 * @param request ServletWebRequest
	 * @return ValidateCode
	 */
	ValidateCode generate(ServletWebRequest request);

}

package com.codeway.auth.validate;

import org.springframework.web.context.request.ServletWebRequest;


/**
 * 校验码处理器，封装不同校验码的处理逻辑
 * 使用Spring中常用的 依赖搜索 技巧
 **/
public interface ValidateCodeProcessor {

	/**
	 * 创建校验码
	 * @param request request
	 */
	void create(ServletWebRequest request) throws Exception;

	/**
	 * 校验验证码
	 * @param servletWebRequest 封装了request和response的
	 * @param bodyString
	 */
	void validate(ServletWebRequest servletWebRequest, String bodyString);

}

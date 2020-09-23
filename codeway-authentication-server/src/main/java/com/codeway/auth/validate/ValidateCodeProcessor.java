package com.codeway.auth.validate;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;


/**
 * 校验码处理器，封装不同校验码的处理逻辑
 * 使用Spring中常用的 依赖搜索 技巧
 **/
public interface ValidateCodeProcessor {

	/**
	 * 创建校验码
	 * @param request request
	 */
	void create(ServletWebRequest request) throws ServletRequestBindingException, IOException;

	/**
	 * 校验验证码
	 * @param servletWebRequest 封装了request和response的
	 * @param bodyString
	 */
	void validate(ServletWebRequest servletWebRequest, String bodyString);

}

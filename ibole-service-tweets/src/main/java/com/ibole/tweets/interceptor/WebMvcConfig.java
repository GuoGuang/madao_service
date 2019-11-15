/*
package com.ibole.tweets.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

*/
/**
 * @description d
 * @author LGG
 * @create 2018-10-29 16:03
 **//*

@Configuration
public class WebMvcConfig  extends WebMvcConfigurerAdapter {

	@Autowired
	private UserLoginHandlerInterceptor globalInterceptor;


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 添加拦截器
		registry.addInterceptor(globalInterceptor);
		super.addInterceptors(registry);
	}
}
*/

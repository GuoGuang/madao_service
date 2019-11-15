/*
package com.ibole.tweets.interceptor;

import org.springframework.context.annotation.ComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

*/
/**
 * @description s
 * @author LGG
 * @create 2018-10-29 16:29
 **//*


@WebFilter
public class    filter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.err.println("filter:init");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		System.err.println("filter:doFilter");
		filterChain.doFilter(servletRequest,servletResponse);
	}

	@Override
	public void destroy() {
		System.err.println("filter:destroy");
	}
}*/

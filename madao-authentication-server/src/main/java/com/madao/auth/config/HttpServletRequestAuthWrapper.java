package com.madao.auth.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

/**
 * HttpServletRequest包装为鉴权专用类
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class HttpServletRequestAuthWrapper extends HttpServletRequestWrapper {

	private final String url;
	private final String method;

	public HttpServletRequestAuthWrapper(HttpServletRequest request, String url, String method) {
		super(request);
		this.url = url;
		this.method = method;
	}

	@Override
	public String getMethod() {
		return this.method;
	}

	@Override
	public String getServletPath() {
		return this.url;
	}
}

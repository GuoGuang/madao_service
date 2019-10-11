package com.youyd.auth.filter;

import com.youyd.auth.token.SmsCodeAuthenticationToken;
import com.youyd.utils.JsonUtil;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 短信登录过滤器
 * @author : LGG
 * @create : 2019-06-18 14:34
 **/

public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	/**
	 * 请求中，参数为phone
	 */
	private static final String PHONE_KEY = "phone";
	private String phoneParameter = PHONE_KEY;
	/**
	 * 是否只处理post请求
	 */
	private boolean postOnly = true;

	public SmsCodeAuthenticationFilter() {
		//要拦截的请求
		super(new AntPathRequestMatcher("/oauth/phone", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (this.postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		} else {
			String phone = this.obtainPhone(request);
			if (StringUtils.isEmpty(phone)) {
				phone = "";
			}

			phone = phone.trim();
			//把手机号传进SmsCodeAuthenticationToken
			SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(phone);
			this.setDetails(request, authRequest);
			//调用AuthenticationManager
			return this.getAuthenticationManager().authenticate(authRequest);
		}
	}

	/**
	 * 获取手机号
	 *
	 * @param request request
	 * @return String
	 */
	private String obtainPhone(HttpServletRequest request) {
		String body = null;
		try {
			body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = JsonUtil.jsonToMap(body);
		String phone = map.get("phone")+"";

		return phone;
	}

	private void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
		authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
	}

	public void setPhoneParameter(String phoneParameter) {
		Assert.hasText(phoneParameter, "phone parameter must not be empty or null");
		this.phoneParameter = phoneParameter;
	}


	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getUsernameParameter() {
		return this.phoneParameter;
	}
}


package com.madao.auth.filter;

import com.madao.auth.token.SmsCodeAuthenticationToken;
import com.madao.utils.JsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 短信登录过滤器
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
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
		super(new AntPathRequestMatcher("/auth/" + PHONE_KEY, "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		if (this.postOnly && !HttpMethod.POST.matches(request.getMethod())) {
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
			log.error(e.getMessage(), e);
		}
		Map<String, Object> map = JsonUtil.jsonToMap(body);
		String phone = map.get(PHONE_KEY) + "";

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


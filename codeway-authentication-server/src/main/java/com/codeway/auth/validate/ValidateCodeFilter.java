package com.codeway.auth.validate;

import com.codeway.auth.config.BodyReaderHttpServletRequestWrapper;
import com.codeway.auth.exception.AuthException;
import com.codeway.enums.ValidateCodeType;
import com.codeway.properties.SecurityProperties;
import com.codeway.utils.HttpHelper;
import com.codeway.utils.LogBack;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 校验验证码的过滤器
 * 指定哪些请求需要验证
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

	/**
	 * 验证码校验失败处理器
	 */
	private final AuthenticationFailureHandler authenticationFailureHandler;

	/**
	 * 系统配置信息
	 */
	private final SecurityProperties securityProperties;

	/**
	 * 系统中的校验码处理器
	 */
	private final ValidateCodeProcessorHolder validateCodeProcessorHolder;

	/**
	 * 存放所有需要校验验证码的url
	 */
	private Map<String, ValidateCodeType> urlMap = new HashMap<>();

	/**
	 * 验证请求url与配置的url是否匹配的工具类
	 */
	private AntPathMatcher pathMatcher = new AntPathMatcher();

	public ValidateCodeFilter(AuthenticationFailureHandler authenticationFailureHandler, SecurityProperties securityProperties, ValidateCodeProcessorHolder validateCodeProcessorHolder) {
		this.authenticationFailureHandler = authenticationFailureHandler;
		this.securityProperties = securityProperties;
		this.validateCodeProcessorHolder = validateCodeProcessorHolder;
	}

	/**
	 * 初始化要拦截的url配置信息
	 */
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		urlMap.put("/oauth/token", ValidateCodeType.CAPTCHA);
		addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.CAPTCHA);

		urlMap.put("/oauth/phone", ValidateCodeType.SMS);
		addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);
	}

	/**
	 * 将系统中配置的需要校验验证码的URL根据校验的类型放入map
	 */
	protected void addUrlToMap(String urlString, ValidateCodeType type) {
		if (StringUtils.isNotBlank(urlString)) {
			String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
			for (String url : urls) {
				urlMap.put(url, type);
			}
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		// 获取requestbody的json值，解决controller中获取不到的问题
		ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
		String bodyString = HttpHelper.getBodyString(requestWrapper);

		ValidateCodeType type = getValidateCodeType(request);
		if (type != null) {
			LogBack.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
			try {
				validateCodeProcessorHolder.findValidateCodeProcessor(type)
						.validate(new ServletWebRequest(request, response), bodyString);
				LogBack.info("验证码校验通过");
			} catch (AuthException exception) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
				return;
			}
		}

		chain.doFilter(requestWrapper, response);

	}

	/**
	 * 获取校验码的类型，如果当前请求不需要校验，则返回null
	 */
	private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
		ValidateCodeType result = null;
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
			Set<String> urls = urlMap.keySet();
			for (String url : urls) {
				if (pathMatcher.match(url, request.getRequestURI())) {
					result = urlMap.get(url);
				}
			}
		}
		return result;
	}

}

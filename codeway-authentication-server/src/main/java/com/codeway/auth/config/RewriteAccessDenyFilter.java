package com.codeway.auth.config;

import com.codeway.enums.StatusEnum;
import com.codeway.utils.JsonData;
import com.codeway.utils.JsonUtil;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 自定义异常，Spring Security异常之外的异常
 **/
public class RewriteAccessDenyFilter extends GenericFilterBean {
	private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (Exception ex) {
//			Throwable[] causeChain = throwableAnalyzer.determineCauseChain(ex);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");
			JsonData<Object> objectJsonData = new JsonData<>(StatusEnum.ACCESS_DENIED);
			response.getWriter().print(JsonUtil.toJsonString(objectJsonData));
		}
	}
}

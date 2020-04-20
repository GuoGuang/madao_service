/*
package com.codeway.gateway.handle;

import com.codeway.enums.StatusEnum;
import com.codeway.utils.JsonData;
import com.codeway.utils.JsonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

*/
/**
 * 用户没有登录时返回给前端的数据
 **//*


@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
//		httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		// 如果这里状态改为HttpServletResponse.SC_UNAUTHORIZED 会导致feign之间调用异常 see https://xujin.org/sc/sc-feign-4xx/
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		JsonData jsonData =  new JsonData(StatusEnum.LOGIN_EXPIRED);
		httpServletResponse.getWriter().write(JsonUtil.toJsonString(jsonData));
	}
}
*/

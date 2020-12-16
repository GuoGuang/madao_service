package com.madaoo.article.handler;

import com.madaoo.enums.StatusEnum;
import com.madaoo.utils.JsonData;
import com.madaoo.utils.JsonUtil;
import com.madaoo.utils.LogBack;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户没有登录时返回给前端的数据
 * 未登陆时返回 JSON 格式的数据给前端（否则为 html）
 **/

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");
//		httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		// 如果这里状态改为HttpServletResponse.SC_UNAUTHORIZED 会导致feign之间调用异常 see https://xujin.org/sc/sc-feign-4xx/
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		LogBack.error("用户没有登录时返回给前端的数据");
		JsonData<Object> jsonData =  new JsonData<>(StatusEnum.LOGIN_EXPIRED);
		httpServletResponse.getWriter().write(JsonUtil.toJsonString(jsonData));
	}
}

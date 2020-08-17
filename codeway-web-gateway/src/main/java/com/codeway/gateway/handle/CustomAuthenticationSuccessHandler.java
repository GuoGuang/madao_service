/*
package com.codeway.gateway.handle;

import com.codeway.enums.StatusEnum;
import com.codeway.model.pojo.ErrotResult;
import com.codeway.utils.JsonUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

*/
/**
 * 用户登录成功时返回给前端的数据
 **//*



@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		ErrotResult errotResult = new ErrotResult(StatusEnum.OK);
		httpServletResponse.getWriter().write(JsonUtil.toJsonString(errotResult));
	}
}

*/

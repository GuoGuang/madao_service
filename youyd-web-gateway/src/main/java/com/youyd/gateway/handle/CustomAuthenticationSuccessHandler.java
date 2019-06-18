/*
package com.youyd.gateway.handle;

import com.youyd.enums.StatusEnum;
import com.youyd.pojo.ErrotResult;
import com.youyd.utils.JsonUtil;
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
 * @author : LGG
 * @create : 2019/6/10 2:03:05
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

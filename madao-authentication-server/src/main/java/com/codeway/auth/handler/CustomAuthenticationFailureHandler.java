package com.madaoo.auth.handler;

import com.madaoo.auth.exception.AuthException;
import com.madaoo.enums.StatusEnum;
import com.madaoo.utils.JsonData;
import com.madaoo.utils.JsonUtil;
import com.madaoo.utils.LogBack;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录失败时返回给前端的数据
 **/
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        LogBack.error("用户登录失败时异常----------->{}",e.getMessage(), e);
        JsonData<Void> errorResult = JsonData.failed(StatusEnum.SYSTEM_ERROR);
        if (e instanceof AuthException) {
            errorResult = JsonData.failed(StatusEnum.LOGIN_ERROR, e.getMessage());
        }
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JsonUtil.toJsonString(errorResult));
    }
}


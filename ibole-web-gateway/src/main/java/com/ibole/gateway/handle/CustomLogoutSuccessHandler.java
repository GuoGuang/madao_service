/*
package com.ibole.gateway.handle;

import com.ibole.enums.StatusEnum;
import com.ibole.pojo.ErrotResult;
import com.ibole.utils.JsonUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

*/
/**
 * 用户登出成功时返回给前端的数据
 **//*


@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		ErrotResult errotResult = new ErrotResult(StatusEnum.OK);
		response.getWriter().write(JsonUtil.toJsonString(errotResult));
	}
}
*/

/*
package com.youyd.gateway.handle;

import com.youyd.enums.StatusEnum;
import com.youyd.pojo.ErrotResult;
import com.youyd.utils.JsonUtil;
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
 * @author : LGG
 * @create : 2019/6/10 2:03:05
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

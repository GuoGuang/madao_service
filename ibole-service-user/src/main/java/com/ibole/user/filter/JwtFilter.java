package com.ibole.user.filter;

import com.ibole.utils.security.JWTAuthentication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 鉴权拦截器
 **/
public class JwtFilter extends HandlerInterceptorAdapter {

	@Autowired
	private JWTAuthentication jwtAuthentication;

	/**
	 * 拦截器-预处理:可以进行编码、安全控制等处理
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		final String authorization = request.getHeader("Authorization");
		if (StringUtils.isNotEmpty(authorization) && StringUtils.startsWith(authorization,"Bearer-")) {
			final String token = authorization.substring(7); // The part after "Bearer "
			/*Claims claims = jwtAuthentication.parseJWT(token);
			if (claims != null) {
				// 管理员角色
				if(StringUtils.equals("admin",claims.get("roles").toString())){
					request.setAttribute("admin_claims", claims);
				}
				// 普通用户
				if(StringUtils.equals("user",claims.get("roles").toString())){
					request.setAttribute("user_claims", claims);
				}
			}*/
		}
		return true;
	}

	/**
	 * 后处理（调用了Service并返回ModelAndView，但未进行页面渲染）
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 返回处理（已经渲染了页面）
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}

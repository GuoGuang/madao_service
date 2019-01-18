package com.youyd.gateway.config;/*
package com.youyd.user.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.youyd.utils.security.JWTAuthentication;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

*/
/**
 * @description: 统一过滤器
 * @author: LGG
 * @create: 2018-11-03 21:54
 **//*

public class WebZuulFilter extends ZuulFilter {

	@Autowired
	private JWTAuthentication jwtAuthentication;

	*/
/**
	 * 返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过
	 * 滤器类型，具体如下：
	 *      pre ：可以在请求被路由之前调用
	 *      route ：在路由请求时候被调用
	 *      post ：在route和error过滤器之后被调用
	 *      error ：处理请求时发生错误时被调用
	 * @return
	 *//*

	@Override
	public String filterType() {//过滤器类型
		return "pre";//前置过滤器
	}

	*/
/**
	 * 通过int值来定义过滤器的执行顺序
	 * @return
	 *//*

	@Override
	public int filterOrder() {
		return 0;//优先级，数字越大，优先级越低
	}

	*/
/**
	 * 返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可
	 * 实现过滤器的开关。在上例中，我们直接返回true，所以该过滤器总是生效
	 * @return
	 *//*

	@Override
	public boolean shouldFilter() {
		return true;//过滤器开关，true表示开启
	}

	*/
/**
	 * 过滤器的具体逻辑。
	 *
	 * 对每一次请求鉴权,如果是登录页面放行
	 * @return
	 *//*

	@Override
	public Object run() {
		System.out.println("Zuul过滤器");
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		String url = request.getRequestURL().toString();

		if (url.indexOf("/admin/login") > 0) {
			System.out.println("登陆页面" + url);
			return null;
		}
		String authHeader = (String) request.getHeader("Authorization");//获取头信息
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			Claims claims = jwtAuthentication.parseJWT(token);
			if (claims != null) {
				if ("admin".equals(claims.get("roles"))) {
					requestContext.addZuulRequestHeader("Authorization", authHeader);
					System.out.println("token 验证通过，添加了头信息"+authHeader);
					return null;
				}
			}
		}
		requestContext.setSendZuulResponse(false);//终止运行
		requestContext.setResponseStatusCode(401);//http状态码
		requestContext.setResponseBody("无权访问");
		requestContext.getResponse().setContentType("text/html;charset=UTF‐8");
		return null;
	}
}*/

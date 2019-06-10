/*
package com.youyd.gateway.config;


import com.youyd.gateway.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

*/
/**
 * 添加 SpringSecurity 依赖后，所有的地址都被 SpringSecurity 所控制
 * @author : LGG
 * @create : 2018-10-21 21:07
 * @see https://blog.csdn.net/jkjkjkll/article/details/79975975
 * @see https://blog.csdn.net/larger5/article/details/81047869
 **//*

@Configuration
@EnableWebFluxSecurity
//@EnableOAuth2Sso // 实现基于OAuth2的单点登录
public class WebSecurityConfig{

	//  未登陆时返回 JSON 格式的数据给前端（否则为 html）
	//@Autowired
	AuthenticationEntryPoint authenticationEntryPoint;

	// 登录成功返回的 JSON 格式数据给前端（否则为 html）
	@Autowired
	AuthenticationSuccessHandler authenticationSuccessHandler;

	// 登录失败返回的 JSON 格式数据给前端（否则为 html）
	@Autowired
	AuthenticationFailureHandler authenticationFailureHandler;

	// 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）
	@Autowired
	LogoutSuccessHandler logoutSuccessHandler;

	// 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）
	@Autowired
	AccessDeniedHandler accessDeniedHandler;

	// 自定义安全认证
	@Autowired
	AuthenticationManager authenticationManager;

	*/
/**
	 * Security安全验证、授权、登录
	 * @param http
	 *//*

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {

		http.authorizeExchange()
				.pathMatchers("/su/user/login").permitAll()  //无需进行权限过滤的请求路径
				.anyExchange().authenticated() // 其他请求都得经过认证和授权
				.and()
				.csrf() // 禁用Cross-site request forgery
				//.csrfTokenRepository(customCsrfTokenRepository)
				//.requireCsrfProtectionMatcher(customCsrfMatcher)
				.and()
				.formLogin()
				.authenticationEntryPoint(authenticationEntryPoint)
				.authenticationFailureHandler(authenticationFailureHandler)
				.authenticationFailureHandler(authenticationFailureHandler)
				.authenticationManager(authenticationManager)
				.and()
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
				.and()
				.logout()
				.logoutSuccessHandler(logoutSuccessHandler)
				.and()

//				.and()
//				.httpBasic().and()
//				.formLogin()
		//.loginPage("/loginPage")  //自定义的登陆页面
		;
		return http.build();
	}
}
*/

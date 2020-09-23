package com.codeway.gateway.handle;

/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


*/
/**
 * 添加 SpringSecurity 依赖后，所有的地址都被 SpringSecurity 所控制
 * @see https://blog.csdn.net/jkjkjkll/article/details/79975975
 * @see https://blog.csdn.net/larger5/article/details/81047869
 **//*


@Configuration
//@EnableWebSecurity
//@EnableOAuth2Sso // 实现基于OAuth2的单点登录
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	//  未登陆时返回 JSON 格式的数据给前端（否则为 html）
	@Autowired
	CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	// 登录成功返回的 JSON 格式数据给前端（否则为 html）
	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	// 登录失败返回的 JSON 格式数据给前端（否则为 html）
	@Autowired
	CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	// 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）
	@Autowired
	CustomLogoutSuccessHandler customLogoutSuccessHandler;

	// 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）
	@Autowired
	CustomAccessDeniedHandler customAccessDeniedHandler;

	// 自定义的安全认证
	@Autowired
	PasswordAuthenticationProvider passwordAuthenticationProvider;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 加入自定义的安全认证
		auth.authenticationProvider(passwordAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.httpBasic().authenticationEntryPoint(customAuthenticationEntryPoint)
				.and()
				.authorizeRequests()
				.antMatchers("/su/user/login","/su/user/logout").permitAll()  //无需进行权限过滤的请求路径
				.anyRequest()
				.authenticated()// 其他 url 需要身份认证
				.and()
				.formLogin()  //开启登录
				.successHandler(customAuthenticationSuccessHandler) // 登录成功
				.failureHandler(customAuthenticationFailureHandler) // 登录失败
				.permitAll()

				.and()
				.logout()
				.logoutSuccessHandler(customLogoutSuccessHandler)
				.permitAll();

		http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler); // 无权访问 JSON 格式的数据
	}


*/
/**
	 * 配置Details类型
	 * @return AuthenticationDetailsSource
	 *//*



@Bean
	public AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource() {
		return context -> new CodeWebAuthenticationDetails(context);
	}



*/
/**
	 * 自定义身份验证管理器：
	 * AuthenticationManager
	 * 一个AuthenticationProvider对应一种认证逻辑，而有时用户即可选择密码登录，也可选择验证码登录，
	 *  这就需要有多个认证逻辑同时存在，即多个AuthenticationProvider 。
	 *  而AuthenticationManager就是用来管理多个AuthenticationProvider的。
	 *
	 *  注意：
	 *  如果authenticate方法中存在多重认证流程，即多个自定义登录认证，那么认证流程则是如下：
	 *      1. 执行第1个authenticationProvider的supports方法，
	 *         结果false会跳到下个authenticationProvider。结果true则执行当前authenticationProvider的认证逻辑。
	 *      2.认证不通过，会抛出相关异常，会直接跳出AuthenticationManager。
	 *      3.认证通过，返回已认证Authentication，同样跳出AuthenticationManager。
	 *      4.如果需要进一步认证，可返回null，跳到下一个authenticationProvider，可实现多重认证。
	 *
	 **//*


	@Override
	protected AuthenticationManager authenticationManager(){
		List list = new ArrayList();
		list.add(passwordAuthenticationProvider);
		return new ProviderManager(list);
	}

}

*/

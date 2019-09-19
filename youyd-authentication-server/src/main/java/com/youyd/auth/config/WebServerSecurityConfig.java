package com.youyd.auth.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.youyd.auth.filter.CaptchaAuthenticationFilter;
import com.youyd.auth.filter.SmsCodeAuthenticationFilter;
import com.youyd.auth.handler.CustomAuthenticationFailureHandler;
import com.youyd.auth.handler.CustomAuthenticationSuccessHandler;
import com.youyd.auth.provider.CaptchaAuthenticationProvider;
import com.youyd.auth.provider.SmsCodeAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * WebServerSecurity配置
 * @author : LGG
 * @create : 2019-06-18 14:34
 **/
@Configuration
@EnableWebSecurity
@Order(-1)
public class WebServerSecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private DruidDataSource dataSource;

	// 短信验证码
	//@Autowired
	//private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

	// 全局过滤器校验码
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;


	@Override
	public void configure(WebSecurity web){
		web.ignoring().antMatchers("/oauth/**","/connect/**");
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		AuthenticationManager manager = super.authenticationManagerBean();
		return manager;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and()
				//.addFilterAt(captchaAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.formLogin()
				.successHandler(customAuthenticationSuccessHandler)
				.failureHandler(customAuthenticationFailureHandler)
				//.failureHandler(customAuthenticationFailureHandler)
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
			.anyRequest().authenticated()
				.and()
				.csrf().disable();

		http.addFilterAfter(smsCodeAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.addFilterAt(captchaAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			;	//添加过滤器，处理系统自定义异常
//			.addFilterAfter(new RewriteAccessDenyFilter(), ExceptionTranslationFilter.class);
		http.apply(validateCodeSecurityConfig);

		// 自定义配置
		/*http.apply(validateCodeSecurityConfig) // 全局配置，过滤器链第一个过滤器
				.and()
				.apply(smsCodeAuthenticationSecurityConfig);*/
	}

	/**
	 * 短信验证码登录配置
	 **/
	@Bean
	public SmsCodeAuthenticationFilter smsCodeAuthenticationFilter() throws Exception {
		SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
		smsCodeAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
		smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
		smsCodeAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
		return smsCodeAuthenticationFilter;
	}

	/**
	 * 图片验证码自定义配置
	 **/
	@Bean
	public CaptchaAuthenticationFilter captchaAuthenticationFilter() throws Exception {
		CaptchaAuthenticationFilter captchaAuthenticationFilter = new CaptchaAuthenticationFilter();
		captchaAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
		captchaAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
		captchaAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
		return captchaAuthenticationFilter;
	}

	@Autowired
	SmsCodeAuthenticationProvider SmsCodeAuthenticationProvider;
	@Autowired
	CaptchaAuthenticationProvider captchaAuthenticationProvider;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(SmsCodeAuthenticationProvider)
			.authenticationProvider(captchaAuthenticationProvider);
	}

	/*@Bean
	public CaptchaAuthenticationFilter captchaAuthenticationFilter() throws Exception {
		CaptchaAuthenticationFilter myFilter = new CaptchaAuthenticationFilter();
		//使过滤器关联当前的authenticationManager
		myFilter.setAuthenticationManager(authenticationManager());
		myFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
		myFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
		return myFilter;
	}
	@Bean
	public SmsCodeAuthenticationFilter smsCodeAuthenticationFilter() throws Exception {
		SmsCodeAuthenticationFilter myFilter = new SmsCodeAuthenticationFilter();
		//使过滤器关联当前的authenticationManager
		myFilter.setAuthenticationManager(authenticationManager());
		myFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
		myFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
		return myFilter;
	}*/

	/*@Autowired
	CaptchaAuthenticationProvider captchaAuthenticationProvider;
	@Autowired
	SmsCodeAuthenticationProvider smsCodeAuthenticationProvider;

	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		List list = new ArrayList();
		list.add(captchaAuthenticationProvider);
		list.add(smsCodeAuthenticationProvider);
		return new ProviderManager(list);
	}*/

	/**
	 * 记住我功能的token存取器配置
	 */
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}


	}
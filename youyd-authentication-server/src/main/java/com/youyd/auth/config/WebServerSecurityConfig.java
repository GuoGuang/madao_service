package com.youyd.auth.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.youyd.auth.filter.CaptchaAuthenticationFilter;
import com.youyd.auth.handler.CustomAuthenticationFailureHandler;
import com.youyd.auth.handler.CustomAuthenticationSuccessHandler;
import com.youyd.auth.provider.CaptchaAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	@Autowired
	private CaptchaAuthenticationConfig captchaAuthenticationConfig;

	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	//  校验码
	//@Autowired
	//private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	// 短信验证码
	//@Autowired
	//private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	//@Autowired
	//private AuthorizeConfigManager authorizeConfigManager;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/oauth/**");
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		AuthenticationManager manager = super.authenticationManagerBean();
		return manager;
	}
	//采用bcrypt对密码进行编码
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and()
				//.addFilterAt(captchaAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.formLogin()
				//.failureHandler(customAuthenticationFailureHandler)
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
			.anyRequest().authenticated()
				.and()
				.csrf().disable()
		.apply(captchaAuthenticationConfig);

	}

	@Bean
	public CaptchaAuthenticationFilter captchaAuthenticationFilter() throws Exception {
		CaptchaAuthenticationFilter myFilter = new CaptchaAuthenticationFilter();
		//使过滤器关联当前的authenticationManager
		myFilter.setAuthenticationManager(authenticationManager());
		myFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
		myFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
		return myFilter;
	}

	public void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProvider());
	}

	// 图片验证码自定义认证登录
	@Bean
	public AuthenticationProvider authenticationProvider() {
		return new CaptchaAuthenticationProvider();
	}

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
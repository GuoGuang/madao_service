package com.madao.auth.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.madao.auth.filter.CaptchaAuthenticationFilter;
import com.madao.auth.filter.GithubAuthenticationFilter;
import com.madao.auth.filter.SmsCodeAuthenticationFilter;
import com.madao.auth.handler.CustomAuthenticationFailureHandler;
import com.madao.auth.handler.CustomAuthenticationSuccessHandler;
import com.madao.auth.handler.OauthLoginSuccessHandler;
import com.madao.auth.provider.CaptchaAuthenticationProvider;
import com.madao.auth.provider.GithubAuthenticationProvider;
import com.madao.auth.provider.SmsCodeAuthenticationProvider;
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
 **/
@Configuration
@EnableWebSecurity
@Order(-1)
public class OauthWebServerSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DruidDataSource dataSource;

	// 短信验证码
	//@Autowired
	//private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

	@Autowired
	private SmsCodeAuthenticationProvider smsCodeAuthenticationProvider;
	@Autowired
	private CaptchaAuthenticationProvider captchaAuthenticationProvider;
	@Autowired
	private GithubAuthenticationProvider githubAuthenticationProvider;

	// 全局过滤器校验码
	@Autowired
	private com.madao.auth.config.ValidateCodeSecurityConfig validateCodeSecurityConfig;

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	private OauthLoginSuccessHandler oauthLoginSuccessHandler;

	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/oauth/**", "/connect/**", "/v2/api-docs", "/swagger-resources/configuration/ui",
				"/swagger-resources", "/swagger-resources/configuration/security",
				"/swagger-ui.html", "/css/**", "/js/**", "/images/**", "/webjars/**", "**/favicon.ico", "/index");

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
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.antMatchers("/v2/api-docs",
						"/configuration/ui",
						"/swagger-resources",
						"/configuration/security",
						"/webjars/**",
						"/swagger-resources/configuration/ui",
						"/swagger-ui.html",
						"/swagger-resources/configuration/security").permitAll()
				.and()
				.csrf().disable()
				.logout(logout -> logout.invalidateHttpSession(false).logoutUrl("/oauth/logout"))

				.oauth2Login(oauth2 -> oauth2
						.authorizationEndpoint(System.out::println)
						.redirectionEndpoint(redirection -> redirection.baseUri("/oauth/login/github"))
						.tokenEndpoint(System.out::println)
						//.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
						.successHandler(oauthLoginSuccessHandler)
				);

		http.addFilterAfter(smsCodeAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilterAt(captchaAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilterAt(githubAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
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


	@Bean
	public GithubAuthenticationFilter githubAuthenticationFilter() throws Exception {
		GithubAuthenticationFilter githubAuthenticationFilter = new GithubAuthenticationFilter();
		githubAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
		githubAuthenticationFilter.setAuthenticationSuccessHandler(oauthLoginSuccessHandler);
		githubAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
		return githubAuthenticationFilter;
	}

	/**
	 * 构建AuthorizationServerConfig.configure(AuthorizationServerEndpointsConfigurer endpoints)
	 * 所需的authenticationManager
	 * 目前支持验证码和手机验证码登录
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(smsCodeAuthenticationProvider)
				.authenticationProvider(captchaAuthenticationProvider)
				.authenticationProvider(githubAuthenticationProvider);
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
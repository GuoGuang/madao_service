package com.madao.auth.config;

import com.madao.auth.filter.CaptchaAuthenticationFilter;
import com.madao.auth.filter.GithubAuthenticationFilter;
import com.madao.auth.filter.SmsCodeAuthenticationFilter;
import com.madao.auth.handler.CustomAuthenticationFailureHandler;
import com.madao.auth.handler.CustomAuthenticationSuccessHandler;
import com.madao.auth.handler.OauthLoginSuccessHandler;
import com.madao.auth.provider.CaptchaAuthenticationProvider;
import com.madao.auth.provider.GithubAuthenticationProvider;
import com.madao.auth.provider.SmsCodeAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.List;

/**
 * WebServerSecurity配置
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Configuration
//@EnableWebSecurity
@Order(-1)
@Slf4j
public class OauthWebServerSecurityConfig {
	@Autowired
	private DataSource dataSource;

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
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	private OauthLoginSuccessHandler oauthLoginSuccessHandler;

	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/auth/**", "/connect/**",
				"/v2/api-docs",
				"/swagger-resources/**",
				"/swagger-ui/**");
	}

	/**
	 * actuator、key禁止访问
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.httpBasic().and()
				.authorizeRequests()
				.requestMatchers(HttpMethod.OPTIONS).permitAll()
				.requestMatchers("/v2/api-docs",
						"/swagger-resources/**",
						"/swagger-ui/**").permitAll()
				.and()
				.csrf().disable()
				.logout(logout -> logout.invalidateHttpSession(false).logoutUrl("/auth/logout"))

				// github登录
				.oauth2Login(oauth2 -> oauth2
						.authorizationEndpoint(System.out::println)
						.redirectionEndpoint(redirection -> redirection.baseUri("/auth/login/github"))
						.tokenEndpoint(System.out::println)
						//.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
						.successHandler(oauthLoginSuccessHandler)
				);

		http.addFilterAfter(smsCodeAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilterAt(captchaAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.addFilterAt(githubAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
		;    //添加过滤器，处理系统自定义异常
//			.addFilterAfter(new RewriteAccessDenyFilter(), ExceptionTranslationFilter.class);
		http.apply(validateCodeSecurityConfig);

		// 自定义配置
		/*http.apply(validateCodeSecurityConfig) // 全局配置，过滤器链第一个过滤器
				.and()
				.apply(smsCodeAuthenticationSecurityConfig);*/
		return http.build();
	}

	/**
	 * 短信验证码登录配置
	 **/
	@Bean
	public SmsCodeAuthenticationFilter smsCodeAuthenticationFilter() throws Exception {
		SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
		smsCodeAuthenticationFilter.setAuthenticationManager(authenticationManager());
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
		captchaAuthenticationFilter.setAuthenticationManager(authenticationManager());
		captchaAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
		captchaAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
		return captchaAuthenticationFilter;
	}


	@Bean
	public GithubAuthenticationFilter githubAuthenticationFilter() throws Exception {
		GithubAuthenticationFilter githubAuthenticationFilter = new GithubAuthenticationFilter();
		githubAuthenticationFilter.setAuthenticationManager(authenticationManager());
		githubAuthenticationFilter.setAuthenticationSuccessHandler(oauthLoginSuccessHandler);
		githubAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
		return githubAuthenticationFilter;
	}

	/**
	 * 构建AuthorizationServerConfig.configure(AuthorizationServerEndpointsConfigurer endpoints)
	 * 所需的authenticationManager
	 * 目前支持验证码和手机验证码登录
	 */
	@Bean
	public AuthenticationManager authenticationManager() {
		List<AuthenticationProvider> providers =  List.of(smsCodeAuthenticationProvider,captchaAuthenticationProvider,githubAuthenticationProvider);
		return new ProviderManager(providers);
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
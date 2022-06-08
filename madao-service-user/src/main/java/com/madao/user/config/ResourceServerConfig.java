package com.madao.user.config;

import com.madao.enums.AuthorityEnum;
import com.madao.user.handler.CustomAccessDeniedHandler;
import com.madao.user.handler.CustomAuthenticationEntryPoint;
import com.madao.utils.security.JWTAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	public static final String PARAM_NAME_ON_AUTHORITY = AuthorityEnum.ROLE_ADMIN.getParamNameOnAuthority();
	/**
	 * 公钥
	 */
	private static final String PUBLIC_KEY = "publickey.txt";
	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;

	@Override
	public void configure(ResourceServerSecurityConfigurer resourceServerSecurityConfigurer) {
		resourceServerSecurityConfigurer
				.tokenStore(tokenStore())
				.authenticationEntryPoint(customAuthenticationEntryPoint)
				.accessDeniedHandler(customAccessDeniedHandler)
				.resourceId("service-user");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.csrf()
				.disable()
				.headers()
				.frameOptions()
				.disable()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/api/**").permitAll()
				.antMatchers("/management/health").permitAll()
				.antMatchers(HttpMethod.GET, "/user/info").permitAll()
				.antMatchers("/management/**").hasAuthority(PARAM_NAME_ON_AUTHORITY)
				.antMatchers(HttpMethod.DELETE).hasAuthority(PARAM_NAME_ON_AUTHORITY)
				.antMatchers(HttpMethod.PUT).hasAuthority(PARAM_NAME_ON_AUTHORITY)
				.antMatchers("/api/su/login/github",
						"/v2/api-docs",
						"/swagger-resources/**",
						"/socket/**",
						"/swagger-ui/**").permitAll()
				.antMatchers("/**").authenticated();
	}


	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setVerifierKey(JWTAuthentication.getPubKey(PUBLIC_KEY));
		return converter;
	}
}

package com.youyd.authorization.config;

import com.youyd.authorization.handler.CustomAccessDeniedHandler;
import com.youyd.authorization.handler.CustomAuthenticationEntryPoint;
import com.youyd.utils.security.JWTAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 资源服务配置
 * 所有资源都会经过此配置
 * @ EnableResourceServer 启用资源服务
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	//  未登陆时返回 JSON 格式的数据给前端（否则为 html）
	@Autowired
	CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	// 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）
	@Autowired
	CustomAccessDeniedHandler CustomAccessDeniedHandler;

	//公钥
	private static final String PUBLIC_KEY = "publickey.txt";

	@Override
	public void configure(ResourceServerSecurityConfigurer resourceServerSecurityConfigurer) {
		resourceServerSecurityConfigurer
				.tokenStore(tokenStore())
				.authenticationEntryPoint(customAuthenticationEntryPoint)
				.accessDeniedHandler(CustomAccessDeniedHandler)
				.resourceId("WEBS");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated();
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
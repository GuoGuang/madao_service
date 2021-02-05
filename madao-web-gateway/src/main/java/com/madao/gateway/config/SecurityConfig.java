package com.madao.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * 网关安全
 * 此配置仅限处理Swagger认证流程
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
		return http
				.csrf().disable()
				.authorizeExchange()
				.pathMatchers("/","/doc/**","/swagger-resources/**","/swagger-ui","/swagger-ui/index.html").authenticated()
				.anyExchange().permitAll()
				.and()
				.httpBasic().disable()
				.formLogin().and()
				.build();
	}


}
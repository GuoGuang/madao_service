package com.madao.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
		return http
				.csrf().disable()
				.authorizeExchange()
				.pathMatchers("/").permitAll()
				.anyExchange().authenticated()
				.and()
				.httpBasic().disable()
				.formLogin().and()
				.build();
	}


}
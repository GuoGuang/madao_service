package com.madao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

	/**
	 * actuator、key禁止访问
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.requestMatchers("/actuator/**").authenticated()
				.requestMatchers("/key/**").authenticated()
				.requestMatchers("/decrypt/**").authenticated()
				.anyRequest().permitAll()
				.and()
				.csrf().disable();
		return http.build();
	}
}

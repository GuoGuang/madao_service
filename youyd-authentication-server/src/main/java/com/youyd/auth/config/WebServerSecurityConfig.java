package com.youyd.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebServerSecurity配置
 * @author : LGG
 * @create : 2019-06-18 14:34
 **/
@Configuration
@EnableWebSecurity
@Order(-1)
public class WebServerSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/auth/login","/auth/logout");
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
		http.csrf().disable()
				.httpBasic().and()
				.formLogin()
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
			.anyRequest().authenticated();

	}

	}
package com.youyd.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @description: 添加 SpringSecurity 依赖后，所有的地址都被 SpringSecurity 所控制了，
 *                  目前只是需要用到BCrypt密码加密的部分，所以要添加一个配置类，配置为所有地址都可以匿名访问
 * @author: LGG
 * @create: 2018-10-21 21:07
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 所有地址都可匿名方法
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/**").permitAll()// 许可所有路径匿名访问
				.anyRequest().authenticated()
				.and().csrf().disable();
		//http.csrf().disable();
	}
}

package com.codeway.gateway;

import com.codeway.db.config.DruidConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * gateway服务网关
 * 系统采用Oauth2的客户端模式和授权码模式两种模式
 **/

@ComponentScan(basePackages = {"com.codeway"}, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
				classes = {DruidConfig.class})
})
@EnableFeignClients(basePackages = "com.codeway.api")
@SpringBootApplication
public class GateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}


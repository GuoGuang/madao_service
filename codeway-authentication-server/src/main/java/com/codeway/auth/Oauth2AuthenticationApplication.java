package com.codeway.auth;

import com.codeway.properties.SecurityProperties;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

/**
 * Oauth2授权服务
 **/
@SpringBootApplication
@EnableFeignClients(basePackages = "com.codeway.api")
@ComponentScan(basePackages = {"com.codeway"})
@EnableDiscoveryClient
@EnableRabbit // 启用RabbitMQ
public class Oauth2AuthenticationApplication {
	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthenticationApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
	}

	@Bean
	public SecurityProperties securityProperties() {
		return new SecurityProperties();
	}
	//采用bcrypt对密码进行编码
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

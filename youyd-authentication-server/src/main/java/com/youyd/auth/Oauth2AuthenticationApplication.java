package com.youyd.auth;

import com.youyd.properties.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Oauth2授权服务
 * @author : LGG
 * @create : 2018-09-26 14:34
 **/
@SpringBootApplication
@EnableFeignClients(basePackages = "com.youyd.api")
@ComponentScan(basePackages = {"com.youyd"})
@EnableDiscoveryClient
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

}

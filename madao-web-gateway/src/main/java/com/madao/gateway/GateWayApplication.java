package com.madao.gateway;

import com.madao.gateway.config.CustomLoadBalancerConfiguration;
import com.madao.utils.DateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

/**
 * gateway服务网关
 * 系统采用Oauth2的客户端模式和授权码模式两种模式
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@EnableFeignClients(basePackages = "com.madao.api")
@SpringBootApplication
@LoadBalancerClients(defaultConfiguration = CustomLoadBalancerConfiguration.class)
public class GateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}

	@PostConstruct
	void started() {
		DateUtil.setDefaultZone();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public WebProperties.Resources resources() {
		return new WebProperties.Resources();
	}
}


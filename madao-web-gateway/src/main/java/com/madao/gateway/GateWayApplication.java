package com.madao.gateway;

import com.madao.annotation.EnableSpringCloudComponent;
import com.madao.config.BasicApplication;
import com.madao.config.JpaConfig;
import com.madao.gateway.config.CustomLoadBalancerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import reactor.core.publisher.Hooks;

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
@EnableSpringCloudComponent
@LoadBalancerClients(defaultConfiguration = CustomLoadBalancerConfiguration.class)
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {JpaConfig.class}))
public class GateWayApplication extends BasicApplication {

	public static void main(String[] args) {
		// 在响应式编程上下文中启用自动上下文传播
		Hooks.enableAutomaticContextPropagation();
		SpringApplication.run(GateWayApplication.class, args);
	}

	@Bean
	public WebProperties.Resources resources() {
		return new WebProperties.Resources();
	}
}


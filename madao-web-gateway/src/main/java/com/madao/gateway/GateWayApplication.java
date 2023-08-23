package com.madao.gateway;

import com.madao.annotation.EnableSpringCloudComponent;
import com.madao.gateway.config.CustomLoadBalancerConfiguration;
import com.madao.utils.DateUtil;
import com.netflix.discovery.AbstractDiscoveryClientOptionalArgs;
import com.netflix.discovery.shared.transport.jersey.TransportClientFactories;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.netflix.eureka.http.EurekaClientHttpRequestFactorySupplier;
import org.springframework.cloud.netflix.eureka.http.RestTemplateDiscoveryClientOptionalArgs;
import org.springframework.cloud.netflix.eureka.http.RestTemplateTransportClientFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class GateWayApplication {

	public static void main(String[] args) {
		// 在响应式编程上下文中启用自动上下文传播
		Hooks.enableAutomaticContextPropagation();
		SpringApplication.run(GateWayApplication.class, args);
	}


	/**
	 * 因为此bug：https://github.com/spring-cloud/spring-cloud-netflix/issues/4185，不得不在此处添加两个Bean
	 */
	@Bean
	@ConditionalOnClass(name = { "org.springframework.web.client.RestTemplate", "org.glassfish.jersey.client.JerseyClient" })
	@ConditionalOnMissingBean(value = { AbstractDiscoveryClientOptionalArgs.class }, search = SearchStrategy.CURRENT)
	public RestTemplateDiscoveryClientOptionalArgs restTemplateDiscoveryClientOptionalArgs(EurekaClientHttpRequestFactorySupplier eurekaClientHttpRequestFactorySupplier) {
		return new RestTemplateDiscoveryClientOptionalArgs(eurekaClientHttpRequestFactorySupplier);
	}
	@Bean
	@ConditionalOnClass(name = { "org.springframework.web.client.RestTemplate", "org.glassfish.jersey.client.JerseyClient" })
	@ConditionalOnMissingBean(value = { TransportClientFactories.class }, search = SearchStrategy.CURRENT)
	public RestTemplateTransportClientFactories restTemplateTransportClientFactories(RestTemplateDiscoveryClientOptionalArgs optionalArgs) {
		return new RestTemplateTransportClientFactories(optionalArgs);
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


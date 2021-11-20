package com.madao.gateway.config;

import com.madao.gateway.supplier.HeaderInstanceServiceInstanceListSupplier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.loadbalancer.core.DiscoveryClientServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class CustomLoadBalancerConfiguration {

	@Bean
	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(DiscoveryClient discoveryClient, Environment env, ConfigurableApplicationContext context) {
		return ServiceInstanceListSupplier.builder()
				.withDiscoveryClient()
				.withBase(new HeaderInstanceServiceInstanceListSupplier(new DiscoveryClientServiceInstanceListSupplier(discoveryClient, env)))
				.build(context);
	}
}


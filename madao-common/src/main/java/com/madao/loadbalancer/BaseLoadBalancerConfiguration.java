package com.madao.loadbalancer;

import com.madao.config.HeaderInstanceLoadBalancer;
import com.madao.constant.FeignConst;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class BaseLoadBalancerConfiguration {

	/**
	 * 根据服务名称获取服务实例列表并从从中选择的功能。
	 */
	@Bean
	public ReactorLoadBalancer<ServiceInstance> baseReactorServiceInstanceLoadBalancer(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
		//创建 RoundRobinLoadBalancer，注意这里注入的是 LazyProvider，这主要因为在注册这个 Bean 的时候相关的 Bean 可能还没有被加载注册，利用 LazyProvider 而不是直接注入所需的 Bean 防止报找不到 Bean 注入的错误。
		return new HeaderInstanceLoadBalancer(loadBalancerClientFactory.getLazyProvider(FeignConst.SERVICE_BASE, ServiceInstanceListSupplier.class), FeignConst.SERVICE_BASE);
	}
}
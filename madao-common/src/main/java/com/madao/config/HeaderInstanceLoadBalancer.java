package com.madao.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.*;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.SelectedInstanceCallback;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 团队开发中一般都是多人协作前后端分离，现在有一个场景是：后端两人前端两人都需要跟前端联调，
 * 但是两位后端的服务都已经注册到了dev环境，前端每次调用接口，网关默认以轮询方式请求，
 * 这肯定不是我们想要的结果。
 * <p>
 * 所以需要自定义负载均衡器，前端配置后端人员的IP实例，则请求会优先请求到指定IP的实例，
 * 不配置则默认走服务器的实例
 *
 * @author GuoGuang
 * @created by guoguang0536@gmail.com / 1831682775@qq.com
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2021/11/14/ 15:27:00
 */
@Slf4j
public class HeaderInstanceLoadBalancer implements ReactorServiceInstanceLoadBalancer {

	public  HeaderInstanceLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId) {
		this.serviceId = serviceId;
		this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
	}

	String serviceId;
	ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

	@Override
	public Mono<Response<ServiceInstance>> choose(Request request) {
		//注入的时候注入的是 Lazy Provider，这里取出真正的 Bean，也就是 ServiceInstanceListSupplier
		ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider
				.getIfAvailable(NoopServiceInstanceListSupplier::new);
		//获取实例列表
		return supplier.get(request)
				.next()
				//从列表中选择一个实例
				.map(serviceInstances -> processInstanceResponse(supplier, serviceInstances,request));
	}

	private Response<ServiceInstance> processInstanceResponse(ServiceInstanceListSupplier supplier,
	                                                          List<ServiceInstance> serviceInstances, Request request) {
		Response<ServiceInstance> serviceInstanceResponse = getInstanceResponse(serviceInstances,request);
		// 如果 ServiceInstanceListSupplier 也实现了 SelectedInstanceCallback，
		// 则执行下面的逻辑进行回调。SelectedInstanceCallback 就是每次负载均衡器选择实例之后进行的回调
		if (supplier instanceof SelectedInstanceCallback && serviceInstanceResponse.hasServer()) {
			((SelectedInstanceCallback) supplier).selectedServiceInstance(serviceInstanceResponse.getServer());
		}
		return serviceInstanceResponse;
	}

	private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances, Request request) {
		if (instances.isEmpty()) {
			log.warn("No servers available for service: " + this.serviceId);
			return new EmptyResponse();
		}

		DefaultRequestContext requestContext = (DefaultRequestContext) request.getContext();
		RequestData clientRequest = (RequestData) requestContext.getClientRequest();
		HttpHeaders headers = clientRequest.getHeaders();

		int pos = Math.abs(new AtomicInteger(new Random().nextInt(1000)).incrementAndGet());
		ServiceInstance instance = instances.get(pos % instances.size());

		for (ServiceInstance serviceInstance : instances) {
			String headersInstance = headers.getFirst("INSTANCE");
			if (StringUtils.isNotBlank(headersInstance) && headersInstance.equals(serviceInstance.getHost())){
					instance = serviceInstance;
					break;
				}
		}
		return new DefaultResponse(instance);
	}
}
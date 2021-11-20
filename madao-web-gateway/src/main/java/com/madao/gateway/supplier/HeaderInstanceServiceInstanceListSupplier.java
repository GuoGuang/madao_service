package com.madao.gateway.supplier;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultRequestContext;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.RequestData;
import org.springframework.cloud.loadbalancer.core.DelegatingServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据请求头携带的实例IP访问服务
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2021-11-17 01:37
 */
public class HeaderInstanceServiceInstanceListSupplier extends DelegatingServiceInstanceListSupplier {

	public HeaderInstanceServiceInstanceListSupplier(ServiceInstanceListSupplier delegate) {
		super(delegate);
	}

	@Override
	public Flux<List<ServiceInstance>> get() {
		return delegate.get();
	}

	@Override
	public Flux<List<ServiceInstance>> get(Request request) {
		return delegate.get(request).map(instances -> filteredByHeaderInstance(instances, request));
	}

	private List<ServiceInstance> filteredByHeaderInstance(List<ServiceInstance> serviceInstances, Request request) {

		DefaultRequestContext requestContext = (DefaultRequestContext) request.getContext();
		RequestData clientRequest = (RequestData) requestContext.getClientRequest();
		HttpHeaders headers = clientRequest.getHeaders();

		List<ServiceInstance> filteredInstances = new ArrayList<>();
		for (ServiceInstance serviceInstance : serviceInstances) {
			String headersInstance = headers.getFirst("INSTANCE");
			if (StringUtils.isNotBlank(headersInstance) && headersInstance.equals(serviceInstance.getHost())) {
				filteredInstances.add(serviceInstance);
			}
		}
		if (!filteredInstances.isEmpty()) {
			return filteredInstances;
		}

		return serviceInstances;
	}
}
package com.youyd.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @description: gateway服务网关配置
 * Spring Cloud Gateway功能：
 *      基于Spring Framework 5，Project Reactor和Spring Boot 2.0构建
 *      能够匹配任何请求属性上的路由。
 *      谓词和过滤器特定于路线。
 *      Hystrix断路器集成。
 *      Spring Cloud DiscoveryClient集成
 *      易于编写谓词和过滤器
 *      请求率限制
 *      路径重写
 * @author: LGG
 * @create: 2018-12-26 14:34
 **/

@Configuration
public class GateWayConfig {

	/**
	 * 自定义拦截规则
	 *     id：固定，不同 id 对应不同的功能，可参考 官方文档
	 *     uri：目标服务地址
	 *     predicates：路由条件
	 *     filters：过滤规则
	 *     stripPrefix(0)：剥夺前缀数字为几则删除路径上的几位，stripPrefix(2) /name/bar/foo -》 http://nameservice/foo.
	 *     lb = load-balanced（负载均衡）
	 * @param builder
	 * @return RouteLocator
	 */
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		// 以下是一对针对请求url前缀进行处理的filter工厂
		// PrefixPathGatewayFilterFactory：添加prefix
		// StripPrefixGatewayFilterFactory：去除prefix
		// StripPrefixGatewayFilterFactory.Config config = new StripPrefixGatewayFilterFactory.Config();
		// config.setParts(1);
		return builder.routes()
				// 用户服务
				.route("user_route", r -> r.path("/user/**").filters(f -> f.stripPrefix(0).prefixPath("/su")).uri("lb://SERVICE-USER"))
				// 基础服务
				.route("base_route", r -> r.path("/base/**").filters(f -> f.stripPrefix(1)).uri("lb://SERVICE-BASE"))
				// 文章服务
				.route("article_route",a ->a.path("/article/**").filters(f -> f.stripPrefix(0).prefixPath("/sa")).uri("lb://SERVICE-ARTICLE"))
				.build();

	}


	//这里为支持的请求头，如果有自定义的header字段请自己添加（不知道为什么不能使用*）
	private static final String ALLOWED_HEADERS = "X-TOKEN,x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN,token,username,client";
	private static final String ALLOWED_METHODS = "*";
	private static final String ALLOWED_ORIGIN = "*";
	private static final String ALLOWED_EXPOSE = "*";
	private static final String MAX_AGE = "18000L";

	/**
	 * 解决gateway跨域访问问题
	 * @return WebFilter
	 */
	@Bean
	public WebFilter corsFilter() {
		return (ServerWebExchange ctx, WebFilterChain chain) -> {
			ServerHttpRequest request = ctx.getRequest();
			if (CorsUtils.isCorsRequest(request)) {
				ServerHttpResponse response = ctx.getResponse();
				HttpHeaders headers = response.getHeaders();
				headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
				headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
				headers.add("Access-Control-Max-Age", MAX_AGE);
				headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
				headers.add("Access-Control-Expose-Headers", ALLOWED_EXPOSE);
				headers.add("Access-Control-Allow-Credentials", "true");
				if (request.getMethod() == HttpMethod.OPTIONS) {
					response.setStatusCode(HttpStatus.OK);
					return Mono.empty();
				}
			}
			return chain.filter(ctx);
		};
	}

}

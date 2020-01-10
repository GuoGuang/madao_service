package com.ibole.gateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * gateway服务网关配置
 * Spring Cloud Gateway功能：
 * 基于Spring Framework 5，Project Reactor和Spring Boot 2.0构建
 * 能够匹配任何请求属性上的路由。
 * 谓词和过滤器特定于路线。
 * Hystrix断路器集成。
 * Spring Cloud DiscoveryClient集成
 * 易于编写谓词和过滤器
 * 请求率限制
 * 路径重写
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
		return builder.routes()
				// 用户服务
				.route("user_route", r -> r.path("/su/**").filters(f -> f.stripPrefix(1)).uri("lb://SERVICE-USER"))
				// 基础服务
				.route("base_route", r -> r.path("/ba/**").filters(f -> f.stripPrefix(1)).uri("lb://SERVICE-BASE"))
				// 文章服务
				.route("article_route", a -> a.path("/ar/**").filters(f -> f.stripPrefix(1)).uri("lb://SERVICE-ARTICLE"))

				// 授权、鉴权、第三方登录
				.route("auth_route", a -> a.path("/oauth/**").filters(f -> f.stripPrefix(0)).uri("lb://AUTHENTICATION-SERVER"))
				.route("social_route", a -> a.path("/social/**").filters(f -> f.stripPrefix(1)).uri("lb://AUTHENTICATION-SERVER"))

				// API
				.route("api_base_route", r -> r.path("/api/ba/**").filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-BASE"))
				.route("api_article_route", a -> a.path("/api/ar/**").filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-ARTICLE"))
				.route("api_user_route", a -> a.path("/api/su/**").filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-USER"))

				.build();
	}

}

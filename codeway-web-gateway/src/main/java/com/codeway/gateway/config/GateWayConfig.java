package com.codeway.gateway.config;


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
 *
 * .and().after 可以实现接口在xx时间后放开，但是网关层尽量保证单纯不要掺杂业务逻辑在其中；
 **/

@Configuration
public class GateWayConfig {

	/**
	 * custom blocking rules
	 * id：固定，不同 id 对应不同的功能，可参考 官方文档
	 * uri：target service address
	 * predicates：routing conditions
	 * filters：filter rule
	 * stripPrefix(0)：剥夺前缀数字为几则删除路径上的几位，stripPrefix(2) /name/bar/foo -》 http://nameservice/foo.
	 * lb = load-balanced（负载均衡）
	 *
	 * @param builder
	 * @return RouteLocator
	 */
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				// user
				.route("user_route", r -> r.path("/su/**").filters(f -> f.stripPrefix(1)).uri("lb://SERVICE-USER"))
				// base
				.route("base_route", r -> r.path("/ba/**").filters(f -> f.stripPrefix(1)).uri("lb://SERVICE-BASE"))
				// article
				.route("article_route", a -> a.path("/ar/**").filters(f -> f.stripPrefix(1)).uri("lb://SERVICE-ARTICLE"))

				// authorization, authentication, third-party login
				.route("auth_route", a -> a.path("/oauth/**").filters(f -> f.stripPrefix(0)).uri("lb://AUTHENTICATION-SERVER"))
				.route("social_route", a -> a.path("/social/**").filters(f -> f.stripPrefix(1)).uri("lb://AUTHENTICATION-SERVER"))

				// API
				.route("api_base_route", r -> r.path("/api/ba/**").filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-BASE"))
				.route("api_auth_route", r -> r.path("/api/oauth/**").filters(f -> f.stripPrefix(1)).uri("lb://AUTHENTICATION-SERVER"))
				.route("oauth2", r -> r.path("/api/oauth2/**").filters(f -> f.stripPrefix(1)).uri("lb://AUTHENTICATION-SERVER"))
				.route("oauth2_login", r -> r.path("/api/login/**").filters(f -> f.stripPrefix(1)).uri("lb://AUTHENTICATION-SERVER"))
				.route("api_article_route", a -> a.path("/api/ar/**")
//						.and().after(ZonedDateTime.now().plusMinutes(10)) // execute in ten minutes
						.filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-ARTICLE"))
				.route("api_user_route", a -> a.path("/api/su/**").filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-USER"))

				// python
				.route("api_movie_route", r -> r.path("/api/movie/**").filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-MOVIE"))
				.build();
	}

}

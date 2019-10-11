package com.youyd.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description gateway服务网关配置
 * Spring Cloud Gateway功能：
 *      基于Spring Framework 5，Project Reactor和Spring Boot 2.0构建
 *      能够匹配任何请求属性上的路由。
 *      谓词和过滤器特定于路线。
 *      Hystrix断路器集成。
 *      Spring Cloud DiscoveryClient集成
 *      易于编写谓词和过滤器
 *      请求率限制
 *      路径重写
 * @author LGG
 * @create 2018-12-26 14:34
 **/

@Configuration
public class GateWayConfig {

	/**
	 * 自定义拦截规则
	 *  目前前后台后端处理程序都在一个微服务里，这里使用前缀区分前后台程序，su、sa等
	 *  前台没有后台管理所以基础服务不需要区分，后面可以考虑区分前后台微服务
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
				/*
				* 此处的f.stripPrefix(0) 0应该为1，减少路径请求，为0是因为user工程里现在是包含前台后台的访问请求，
				* 这块后边是否需要优化为前台后台请求分为不同工程有待商榷
				*
				* */
				.route("user_route", r -> r.path("/su/**").filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-USER"))
				// 基础服务
				.route("base_route", r -> r.path("/base/**").filters(f -> f.stripPrefix(1)).uri("lb://SERVICE-BASE"))
				.route("api_base_route", r -> r.path("/api/base/**").filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-BASE"))
				// 微博服务
				.route("tweet_route", r -> r.path("/ts/**").filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-TWEETS"))
				// 文章服务
				.route("article_route",a ->a.path("/article/**").uri("lb://SERVICE-ARTICLE"))
				.route("api_article_route",a ->a.path("/api/article/**").filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-ARTICLE"))
				// 授权、鉴权、第三方登录
				.route("auth_route",a ->a.path("/oauth/**").filters(f -> f.stripPrefix(0)).uri("lb://AUTHENTICATION-SERVER"))
				.route("social_route",a ->a.path("/social/**").filters(f -> f.stripPrefix(1)).uri("lb://AUTHENTICATION-SERVER"))
				.build();

	}
}

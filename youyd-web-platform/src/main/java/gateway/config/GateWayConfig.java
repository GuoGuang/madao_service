package gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
				.route("user_route", r -> r.path("/service_user/**").filters(f -> f.stripPrefix(1)).uri("lb://SERVICE-USER"))
				// 文章服务
				.route("article_route",a ->a.path("/service_article/**").filters(f -> f.stripPrefix(1)).uri("lb://SERVICE-ARTICLE"))
				.build();
	}

}

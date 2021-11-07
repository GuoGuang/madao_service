package com.madao.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
@Component
@Primary
public class SwaggerResourceConfig implements SwaggerResourcesProvider {

	private final RouteLocator routeLocator;
	private final GatewayProperties gatewayProperties;

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
		List<String> routes = new ArrayList<>();
		routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
		//过滤出配置文件中定义的路由->过滤出Path Route Predicate->根据路径拼接成api-docs路径->生成SwaggerResource，这里只处理xx.yml文件中的配置
		gatewayProperties.getRoutes().forEach(route -> route.getPredicates().stream()
				.filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
				.forEach(predicateDefinition -> resources.add(swaggerResource(route.getId(),
						predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
								.replace("**", "v2/api-docs")))));

		return resources;
	}

	private SwaggerResource swaggerResource(String name, String location) {
		log.info("name:{},location:{}", name, location);
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion("2.0");
		return swaggerResource;
	}

	public SwaggerResourceConfig(RouteLocator routeLocator, GatewayProperties gatewayProperties) {
		this.routeLocator = routeLocator;
		this.gatewayProperties = gatewayProperties;
	}
}
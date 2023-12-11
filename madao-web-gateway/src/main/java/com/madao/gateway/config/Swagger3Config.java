package com.madao.gateway.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("dev")
public class Swagger3Config {

	@Bean
	@Lazy(false)
	public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters, RouteDefinitionLocator locator) {
		List<GroupedOpenApi> groups = new ArrayList<>();
		List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
		for (RouteDefinition definition : definitions) {
			System.out.println("id: " + definition.getId()+ "  "+definition.getUri().toString());
		}
		definitions.forEach(routeDefinition -> {
			String name = routeDefinition.getUri().getHost().replaceAll("SERVICE-", "").toLowerCase();
			swaggerUiConfigParameters.addGroup(routeDefinition.getId());
			GroupedOpenApi.builder().pathsToMatch("/" + routeDefinition.getId() + "/**").group(name).build();
		});
		return groups;
	}
}
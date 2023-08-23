package com.madao.gateway.config;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.core.SwaggerUiOAuthProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("dev")
@Import({ org.springdoc.core.SpringDocConfigProperties.class,
		org.springdoc.webmvc.core.MultipleOpenApiSupportConfiguration.class,
		org.springdoc.core.SpringDocConfiguration.class, org.springdoc.webmvc.core.SpringDocWebMvcConfiguration.class,
		SwaggerUiConfigParameters.class, SwaggerUiOAuthProperties.class,
		org.springdoc.core.SwaggerUiConfigProperties.class, org.springdoc.core.SwaggerUiOAuthProperties.class,
		org.springdoc.webmvc.ui.SwaggerConfig.class,
		org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration.class })
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
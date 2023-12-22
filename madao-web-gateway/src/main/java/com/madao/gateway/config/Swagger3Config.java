package com.madao.gateway.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
@Profile("dev")
public class Swagger3Config {

    @Bean
    public List<GroupedOpenApi> groupedOpenApis(SwaggerUiConfigParameters swaggerUiConfigParameters, RouteDefinitionLocator locator) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        Objects.requireNonNull(definitions).forEach(routeDefinition -> {
            String name = routeDefinition.getUri().getHost().replaceAll("SERVICE-", "").toLowerCase();
            swaggerUiConfigParameters.addGroup(routeDefinition.getId());
            groups.add(GroupedOpenApi.builder().pathsToMatch("/" + routeDefinition.getId() + "/**").group(name).build());
        });
        return groups;
    }
}
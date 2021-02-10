package gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * gateway config
 **/

@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user_route", r -> r.path("/user/**").filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-USER"))
                .route("article_route", a -> a.path("/article/**").filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-ARTICLE"))
                .route("tags_route", a -> a.path("/tags/**").filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-ARTICLE"))
                .route("tweets_route", a -> a.path("/tweets/**").filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-TWEETS"))
                .route("gather_route", a -> a.path("/gather/**").filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-GATHER"))
                .route("question_route", a -> a.path("/question/**").filters(f -> f.stripPrefix(0)).uri("lb://SERVICE-QUESTION"))
                .build();
    }

}

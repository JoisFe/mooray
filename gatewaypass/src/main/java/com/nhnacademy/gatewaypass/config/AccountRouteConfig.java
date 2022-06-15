package com.nhnacademy.gatewaypass.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountRouteConfig {
    @Bean
    public RouteLocator accountRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
            .route(p -> p.path("/member/**")
                .uri("http://localhost:8081"))
            .route(p -> p.path("/project/**")
                .uri("http://localhost:8082"))
            .build();
    }
}

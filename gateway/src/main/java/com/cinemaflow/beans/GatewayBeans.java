package com.cinemaflow.beans;

import com.cinemaflow.filters.AuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Set;

@Configuration
@AllArgsConstructor
public class GatewayBeans {

    private final AuthFilter authFilter;

    @Bean
    @Profile(value = "eureka-off")
    public RouteLocator routerLocatorEurekaOff(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route( route -> route
                        .path("/pelicula-service/**")
                        .uri("http://localhost:8090")
                )
                .route(route -> route
                        .path("/auth-server/auth/**")
                        .uri("lb://auth-server")
                )
                .build();
    }

    @Bean
    @Profile(value = "eureka-on")
    public RouteLocator routerLocatorEurekaOn(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("pelicula-service", r -> r
                        .path("/pelicula-service/**")
                        .filters(f -> f.rewritePath("/pelicula-service(?<segment>/.*)", "$\\{segment}"))
                        .uri("lb://pelicula-service")
                )
                .route(route -> route
                        .path("/auth-server/auth/**")
                        .uri("lb://auth-server")
                )
                .route(route -> route
                        .path("/auth-server/auth/**")
                        .uri("lb://auth-server")
                )
                .build();
    }

    @Bean
    @Profile(value = "eureka-on-cb")
    public RouteLocator routerLocatorEurekaOnCB(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("pelicula-service", r -> r
                        .path("/pelicula-service/**")
//                        .filters(f -> f.rewritePath("/pelicula-service(?<segment>/.*)", "$\\{segment}"))
                        .filters(filter -> {
                          filter.circuitBreaker(config -> config
                              .setName("gateway-cb")
                              .setStatusCodes(Set.of("500", "400"))
                               .setFallbackUri("forward:/companies-crud-fallback/company/*"));
                          return filter;
                        })
                        .uri("lb://pelicula-service")
                )
                .route(route -> route
                        .path("/auth-server/auth/**")
                        .uri("lb://auth-server")
                )
                .build();
    }

    @Bean
    @Profile(value = "oauth2")
    public RouteLocator routerLocatorEoauth2(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("pelicula-service", r -> r
                                .path("/pelicula-service/**")
                                .filters(filter -> {
                                    filter.circuitBreaker(config -> config
                                            .setName("gateway-cb")
                                            .setStatusCodes(Set.of("500", "400"))
                                            .setFallbackUri("forward:/auth-server/auth/**"));
                                    filter.filter(this.authFilter);
                                    return filter;
                                })
                                .uri("lb://pelicula-service")
                )
                .route(route -> route
                        .path("/auth-server/auth/**")
                        .uri("lb://auth-server")
                )
                .build();

    }


}

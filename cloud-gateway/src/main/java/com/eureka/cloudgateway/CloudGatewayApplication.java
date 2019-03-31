package com.eureka.cloudgateway;

import com.eureka.cloudgateway.filter.ElapsedFilter;
import com.eureka.cloudgateway.filter.ElapsedGatewayFilterFactory;
import com.eureka.cloudgateway.filter.RateLimitByIpGatewayFilter;
import com.eureka.cloudgateway.filter.TokenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@SpringBootApplication
public class CloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        // @formatter:off
        return builder.routes()
                .route(
                        r -> r.path("/fluent/customer/**")
                                .filters(f -> f.stripPrefix(2)
                                        .filter(new ElapsedFilter())
                                        .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                                .uri("lb://CONSUMER")
                                .order(0)
                                .id("fluent_customer_service")
                )
                .route(r -> r.path("/throttle/customer/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter(new RateLimitByIpGatewayFilter(10, 1, Duration.ofSeconds(1)))
                        .uri("lb://CONSUMER")
                        .order(0)
                        .id("throttle_customer_service")
                )
                .build();
        // @formatter:on
    }

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

    @Bean
    public ElapsedGatewayFilterFactory elapsedGatewayFilterFactory() {
        return new ElapsedGatewayFilterFactory();
    }

}

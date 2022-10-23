package com.student.app;

import com.student.app.config.ApiUrl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("companies", r -> r.host("localhost:8080")
                        .and()
                        .path(ApiUrl.URL + "/companies",
                                ApiUrl.URL + "/companies/get-all",
                                ApiUrl.URL + "/companies/{companyId}",
                                ApiUrl.URL + "/companies/update/{companyId}",
                                ApiUrl.URL + "/companies/update/**",
                                ApiUrl.URL + "/companies/name/{companyName}",
                                ApiUrl.URL + "/companies/name/{companyName}/get-id",
                                ApiUrl.URL + "/companies/name/**"
                        )
                        .uri("http://localhost:8081"))
                .route("drivers", r -> r.host("localhost:8080")
                        .and()
                        .path(ApiUrl.URL + "/drivers",
                                ApiUrl.URL + "/drivers/**",
                                ApiUrl.URL + "/drivers/{name}/companies",
                                ApiUrl.URL + "/drivers/{name}/companies/**",
                                ApiUrl.URL + "/drivers/companies/update/{companyName}",
                                ApiUrl.URL + "/drivers/update/{driverId}")
                        .uri("http://localhost:8082"))
                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {

        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        corsConfig.addAllowedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

}

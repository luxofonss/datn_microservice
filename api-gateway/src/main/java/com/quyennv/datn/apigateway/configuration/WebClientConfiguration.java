package com.quyennv.datn.apigateway.configuration;

import com.quyennv.datn.apigateway.repository.IdentityClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfiguration {
    @Bean
    WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl("http://localhost:8088/users")
                .build();
    }

    @Bean
    IdentityClient identityClient(WebClient webClient) {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();

        return factory.createClient(IdentityClient.class);
    }
}

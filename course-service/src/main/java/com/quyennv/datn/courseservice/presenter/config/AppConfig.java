package com.quyennv.datn.courseservice.presenter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.JsonMessageConverter;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    JsonMessageConverter converter() {
        return new JsonMessageConverter();
    }
}

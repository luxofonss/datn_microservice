package com.quyennv.datn.assignment_service.presenter.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.JsonMessageConverter;

@Configuration
public class KafkaProducerConfig {
    @Bean
    NewTopic assignmentCreated() {
        return new NewTopic("assignment_created", 1, (short) 1);
    }

    @Bean
    JsonMessageConverter converter() {
        return new JsonMessageConverter();
    }

}

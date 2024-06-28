package com.quyennv.datn.notification_service.config;

import com.quyennv.datn.notification_service.kafka.NotificationCreatedDeserialization;
import com.quyennv.datn.notification_service.kafka.NotificationCreatedEvent;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConcurrentKafkaListenerContainerFactory
            <String, NotificationCreatedEvent> notificationKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, NotificationCreatedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerAssignmentFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, NotificationCreatedEvent> consumerAssignmentFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", "localhost:29092");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", NotificationCreatedDeserialization.class.getName());
        props.put("auto.offset.reset", "earliest");

        return new DefaultKafkaConsumerFactory<>(props);
    }
}

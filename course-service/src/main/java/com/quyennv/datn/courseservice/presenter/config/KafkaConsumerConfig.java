package com.quyennv.datn.courseservice.presenter.config;

import com.quyennv.datn.courseservice.adapter.event_consumer.kafka.AssignmentCreatedEvent;
import com.quyennv.datn.courseservice.adapter.event_consumer.kafka.AssignmentCreatedEventDeserializer;
import com.quyennv.datn.courseservice.adapter.event_consumer.kafka.AssignmentDataConsumer;
import com.quyennv.datn.courseservice.core.domain.entities.User;
import com.quyennv.datn.courseservice.adapter.event_consumer.kafka.cdc.MessageCDCDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
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
    public ConcurrentKafkaListenerContainerFactory<String, User> userKafkaListenerContainerFactory(
            ConsumerFactory<String, User> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, User> consumerUserFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", "localhost:29092");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, MessageCDCDeserializer.class.getName());
        props.put("auto.offset.reset", "earliest");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    // AssignmentDataConsumer factory and listener container
    @Bean
    public ConcurrentKafkaListenerContainerFactory
            <String, AssignmentCreatedEvent> assignmentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AssignmentCreatedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerAssignmentFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, AssignmentCreatedEvent> consumerAssignmentFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", "localhost:29092");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", AssignmentCreatedEventDeserializer.class.getName());
        props.put("auto.offset.reset", "earliest");

        return new DefaultKafkaConsumerFactory<>(props);
    }
}

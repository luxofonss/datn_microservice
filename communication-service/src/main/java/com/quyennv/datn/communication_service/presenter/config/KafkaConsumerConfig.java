package com.quyennv.datn.communication_service.presenter.config;

import com.quyennv.datn.communication_service.adapter.event_consumer.kafka.cdc.MessageCDCDeserializer;
import com.quyennv.datn.communication_service.core.domain.entities.User;
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
}

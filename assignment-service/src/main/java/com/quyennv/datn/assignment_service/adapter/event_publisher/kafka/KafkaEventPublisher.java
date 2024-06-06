package com.quyennv.datn.assignment_service.adapter.event_publisher.kafka;

import com.quyennv.datn.assignment_service.core.usecases.EventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventPublisher implements EventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaEventPublisher(KafkaTemplate<String,Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(Object event) {
        String topic = "assignment_created";
        kafkaTemplate.send(topic, event);
    }
}

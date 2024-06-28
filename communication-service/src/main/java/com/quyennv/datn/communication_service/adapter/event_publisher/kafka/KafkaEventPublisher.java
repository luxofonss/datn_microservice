package com.quyennv.datn.communication_service.adapter.event_publisher.kafka;

import com.quyennv.datn.communication_service.core.usecases.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class KafkaEventPublisher implements EventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaEventPublisher(
            KafkaTemplate<String,Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public <T> void publish(T event, String topic) {
        log.info("send event:: {}", event.toString());
        CompletableFuture.runAsync(() -> {
            kafkaTemplate.send(topic, event);

            log.info("Published notification event");
        }).exceptionally(e -> {
            log.error("Error while publishing notification:: {}", e.getMessage());
            return null;
        });
        
    }
}

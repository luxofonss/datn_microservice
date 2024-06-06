package com.quyennv.datn.courseservice.adapter.event_consumer.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class AssignmentCreatedEventDeserializer implements Deserializer<AssignmentCreatedEvent> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }
    @Override
    public AssignmentCreatedEvent deserialize(String s, byte[] bytes) {
        try {
            return objectMapper.readValue(bytes, AssignmentCreatedEvent.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public AssignmentCreatedEvent deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}

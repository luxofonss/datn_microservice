package com.quyennv.datn.assignment_service.adapter.event_consumer.kafka.cdc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quyennv.datn.assignment_service.adapter.event_consumer.kafka.cdc.user.UserAfter;
import com.quyennv.datn.assignment_service.adapter.event_consumer.kafka.cdc.user.UserBefore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageCDC {
    private UserBefore before;
    private UserAfter after;
    private Source source;
    private String op;
    @JsonProperty("ts_ms")
    private Long ts;
    private Object transaction;
}

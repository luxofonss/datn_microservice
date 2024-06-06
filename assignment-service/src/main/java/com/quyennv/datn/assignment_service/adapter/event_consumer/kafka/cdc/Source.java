package com.quyennv.datn.assignment_service.adapter.event_consumer.kafka.cdc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Source {
    private String version;
    private String connector;
    private String name;
    @JsonProperty("ts_ms")
    private Long tsMs;

    private String snapshot;
    private String db;
    private String schema;
    private String table;
    private int txId;
    private int lns;
    private Object xmin;
}

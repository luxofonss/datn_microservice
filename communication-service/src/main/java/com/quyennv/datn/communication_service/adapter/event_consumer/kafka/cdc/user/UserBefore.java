package com.quyennv.datn.communication_service.adapter.event_consumer.kafka.cdc.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBefore {
    private String id;
    @JsonProperty("created_at")
    private Long createdAt;
    @JsonProperty("deleted_at")
    private Long deletedAt;
    @JsonProperty("updated_at")
    private Long updatedAt;
    private String avatar;
    private Long dob;
    private String email;
    private String firstName;
    private String gender;
    private String verified;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String role;
    private String username;
}

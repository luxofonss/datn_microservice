package com.quyennv.datn.notification_service.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Notification {
    private UUID id;

    private String type;

    private UUID studentId;

    private UUID teacherId;

    private UUID destinationId;

    private UUID courseId;

    private UUID lessonId;

    private UUID conversationId;

    private UUID assignmentAttemptId;

    private String placementType;

    private String studentName;

    private String message;

    private Boolean isSeen;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;
}

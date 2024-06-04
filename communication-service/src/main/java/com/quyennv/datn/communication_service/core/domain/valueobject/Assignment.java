package com.quyennv.datn.communication_service.core.domain.valueobject;

import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Slf4j
@ToString
public class Assignment {
    private Identity id;
    private String title;
    private String description;
    private Integer totalMark;

    private Long duration;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String assignmentType;
    private Long maxAttemptTimes;
    private User creator;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

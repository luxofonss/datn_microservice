package com.quyennv.datn.courseservice.core.domain.entities;

import com.quyennv.datn.courseservice.core.domain.enums.LearnerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LearnerInfo {
    private Identity id;
    private Identity userId;
    private User user;
    private LearnerType type;
    private Integer grade;
    private String school;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

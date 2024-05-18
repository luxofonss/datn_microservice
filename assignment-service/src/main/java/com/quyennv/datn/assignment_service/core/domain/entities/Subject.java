package com.quyennv.datn.assignment_service.core.domain.entities;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Subject {
    private Identity id;
    private String name;
    private String description;
    private String thumbnail;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

package com.quyennv.datn.communication_service.core.domain.valueobject;

import com.quyennv.datn.communication_service.core.domain.entities.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    UUID id;
    String name;
    String description;
    String backgroundImage;
    String thumbnail;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Long price;
    String level;
    String status;
    Integer grade;
    String code;
    List<Conversation> conversations;
    User teacher;
    List<CourseStudent> students;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;

    @Data
    public static class CourseStudent {
        User student;
        String status;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;
        LocalDateTime deletedAt;
    }
}

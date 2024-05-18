package com.quyennv.datn.assignment_service.core.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.entities.LessonStudent;
import com.quyennv.datn.courseservice.core.domain.entities.Section;
import com.quyennv.datn.courseservice.core.domain.enums.LessonType;
import com.quyennv.datn.courseservice.core.domain.valueobject.Assignment;
import com.quyennv.datn.courseservice.core.domain.valueobject.Resource;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Lesson {
    private Identity id;
    private LessonType type;
    private String name;
    private String description;
    private Integer order;
    private Resource resource;
    private Section section;
    private Assignment assignment;
    @JsonIgnore
    private List<LessonStudent> lessonStudents;
    private LessonStudent lessonStudent;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public void delete() {
        this.setDeletedAt(LocalDateTime.now());
    }
}
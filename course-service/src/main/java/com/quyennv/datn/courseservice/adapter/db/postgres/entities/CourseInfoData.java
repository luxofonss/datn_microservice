package com.quyennv.datn.courseservice.adapter.db.postgres.entities;

import com.quyennv.datn.courseservice.core.domain.entities.CourseInfo;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.enums.CourseInfoType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity(name="course_infos")
@Getter
@Setter
@ToString(exclude = "course")
@Table(name="course_infos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseInfoData extends BaseEntity {
    private String content;
    @Enumerated(EnumType.STRING)
    private CourseInfoType type;

    @ManyToOne
    @JoinColumn(name="course_id", nullable = false)
    private CourseData course;

    public static CourseInfoData from(CourseInfo ci) {
        CourseInfoData result = CourseInfoData
                .builder()
                .content(ci.getContent())
                .type(ci.getType())
                .build();

        result.setCreatedAt(ci.getCreatedAt());
        result.setUpdatedAt(ci.getUpdatedAt());
        result.setDeletedAt(ci.getDeletedAt());
        if (Objects.nonNull(ci.getId())) {
            result.setId(ci.getId().getId());
        }
        return result;
    }

    public CourseInfo fromThis() {
        return CourseInfo
                .builder()
                .id(Objects.nonNull(this.getId()) ? Identity.from(this.getId()) : null)
                .content(this.content)
                .type(this.type)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}

package com.quyennv.datn.courseservice.adapter.db.postgres.entities;

import com.quyennv.datn.courseservice.core.domain.entities.Section;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@Entity(name="sections")
@Getter
@Setter
@ToString(exclude = {"course", "lessons"})
@Table(name="sections")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class SectionData extends BaseEntity {
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name="course_id", nullable = false)
    private CourseData course;
    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LessonData> lessons;

    public static SectionData from(Section s) {
        SectionData result = SectionData
                .builder()
                .name(s.getName())
                .description(s.getDescription())
                .build();

        if (Objects.nonNull(s.getId())) {
            result.setId(s.getId().getId());
        }

        result.setCreatedAt(s.getCreatedAt());
        result.setUpdatedAt(s.getUpdatedAt());
        result.setDeletedAt(s.getDeletedAt());

        if (Objects.nonNull(s.getCourse())) {
            result.setCourse(CourseData.from(s.getCourse()));
        }

        if (Objects.nonNull(s.getLessons())) {
            result.setLessons(s.getLessons().stream().map(l -> {
                LessonData lesson = LessonData.from(l);
                lesson.setSection(result);
                return lesson;
            }).toList());
        }

        return result;
    }

    public Section fromThis() {
        Section result = Section
                .builder()
                .id(Identity.from(this.getId()))
                .name(this.getName())
                .description(this.getDescription())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();

        if (Objects.nonNull(this.lessons)) {
            result.setLessons(this.lessons.stream().map(LessonData::fromThis).toList());
        }

        return result;
    }

    public Section info() {

        return Section
                .builder()
                .id(Identity.from(this.getId()))
                .name(this.getName())
                .description(this.getDescription())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}

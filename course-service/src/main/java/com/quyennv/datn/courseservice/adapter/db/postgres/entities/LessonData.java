package com.quyennv.datn.courseservice.adapter.db.postgres.entities;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.entities.Lesson;
import com.quyennv.datn.courseservice.core.domain.enums.LessonType;
import com.quyennv.datn.courseservice.core.domain.valueobject.Resource;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity(name="lessons")
@Getter
@Setter
@ToString(exclude = {"section"})
@Table(name="lessons")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonData extends BaseEntity {
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private LessonType type;
    @Column(name="lesson_order")
    private Integer order;

    @Column(name="resource_id")
    private UUID resourceId;

    @ManyToOne
    @JoinColumn(name="section_id", nullable = false)
    private SectionData section;

    @OneToMany(mappedBy = "lesson", fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    private List<LessonStudentData> lessonStudents;

    public static LessonData from(Lesson l) {
        LessonData result = LessonData
                .builder()
                .type(l.getType())
                .name(l.getName())
                .description(l.getDescription())
                .order(l.getOrder())
                .resourceId(Objects.nonNull(l.getResource())
                        ? l.getResource().getId().getId()
                        : null)
                .build();


        result.setCreatedAt(l.getCreatedAt());
        result.setUpdatedAt(l.getUpdatedAt());
        result.setDeletedAt(l.getDeletedAt());

        if (Objects.nonNull(l.getId())) {
            result.setId(l.getId().getId());
        }

        if (Objects.nonNull(l.getSection())) {
            result.setSection(SectionData.from(l.getSection()));
        }

        if (Objects.nonNull(l.getLessonStudents())) {
            result.setLessonStudents(l.getLessonStudents().stream().map(LessonStudentData::from).toList());
        }

        return result;
    }

    public Lesson fromThis() {
        Lesson result = Lesson
                .builder()
                .name(this.name)
                .type(this.type)
                .description(this.description)
                .order(this.order)
                .resource(Objects.nonNull(this.resourceId)
                        ? Resource.builder().id(Identity.from(this.resourceId)).build()
                        : null)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();

        if (Objects.nonNull(this.getId())) {
            result.setId(Identity.from(this.getId()));
        }

        if (Objects.nonNull(this.getLessonStudents())) {
            result.setLessonStudents(this.lessonStudents.stream().map(LessonStudentData::fromThis).toList());
        }

        return result;
    }
}

package com.quyennv.datn.courseservice.adapter.db.postgres.entities;

import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.enums.CourseLevel;
import com.quyennv.datn.courseservice.core.domain.enums.CourseStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity(name="courses")
@Table()
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Slf4j
@ToString(exclude = {"sections", "courseInfos", "subject", "courseStudents"})
public class CourseData extends BaseEntity{
    private String name;
    private String description;
    @Column(name="background_img")
    private String backgroundImage;
    private String thumbnail;
    @Column(name="start_date")
    private LocalDateTime startDate;
    @Column(name="end_date")
    private LocalDateTime endDate;
    private Long price;
    @Enumerated(EnumType.STRING)
    private CourseLevel level;
    @Enumerated(EnumType.STRING)
    private CourseStatus status;
    private Integer grade;
    private String code;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private List<SectionData> sections;
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseInfoData> courseInfos;
    @ManyToOne
    @JoinColumn(name="subject_id")
    private SubjectData subject;

//    @Column(name="teacher_id", nullable = false)
//    private UUID teacherId;
    @ManyToOne
    @JoinColumn(name="teacher_id", nullable = false)
    private UserData teacher;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    private List<CourseStudentData> courseStudents;


    public static CourseData from(Course course) {
        CourseData result = CourseData
                .builder()
                .name(course.getName())
                .description(course.getDescription())
                .backgroundImage(course.getBackgroundImage())
                .thumbnail(course.getThumbnail())
                .startDate(course.getStartDate())
                .endDate(course.getEndDate())
                .price(course.getPrice())
                .level(course.getLevel())
                .status(course.getStatus())
                .grade(course.getGrade())
                .code(course.getCode())
                .subject(Objects.nonNull(course.getSubject()) ? SubjectData.from(course.getSubject()) : null)
                .teacher(Objects.nonNull(course.getTeacher()) ? UserData.from(course.getTeacher()) : null)
                .build();

        if (Objects.nonNull(course.getId())) {
            result.setId(course.getId().getId());
        } else {
            result.setId(UUID.randomUUID());
        }

        result.setCreatedAt(course.getCreatedAt());
        result.setUpdatedAt(course.getUpdatedAt());
        result.setDeletedAt(course.getDeletedAt());

        if (Objects.nonNull(course.getSections())) {
            result.setSections(course.getSections().stream().map(s -> {
                SectionData section = SectionData.from(s);
                section.setCourse(result);
                return section;
            }).toList());
        }

        if (Objects.nonNull(course.getCourseInfos())) {
            result.setCourseInfos(course.getCourseInfos().stream().map(i -> {
                CourseInfoData courseInfo = CourseInfoData.from(i);
                courseInfo.setCourse(result);
                return courseInfo;
            }).toList());
        }

        if (Objects.nonNull(course.getStudents())) {
            result.setCourseStudents(course.getStudents().stream().map(s -> {
                CourseStudentData student = CourseStudentData.from(s);
                student.setCourse(result);
                return student;
            }).toList());
        }

        return result;
    }

    public static CourseData newWithId(Identity id) {
        CourseData data = new CourseData();
        data.setId(id.getId());
        return data;
    }

    public Course fromThis() {
        Course course = Course
                .builder()
                .id(Identity.from(this.getId()))
                .name(this.name)
                .description(this.description)
                .backgroundImage(this.backgroundImage)
                .thumbnail(this.thumbnail)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .price(this.price)
                .level(this.level)
                .status(this.status)
                .grade(this.grade)
                .code(this.code)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();

        if (Objects.nonNull(this.getCourseInfos())) {
            course.setCourseInfos(this.courseInfos.stream().map(CourseInfoData::fromThis).toList());
        }

        if (Objects.nonNull(this.sections)) {
            course.setSections(this.sections.stream().map(SectionData::fromThis).toList());
        }

        if (Objects.nonNull(this.subject)) {
            course.setSubject(this.subject.fromThis());
        }

        if (Objects.nonNull(this.teacher)) {
            course.setTeacher(this.teacher.fromThis());
        }

        if (Objects.nonNull(this.teacher)) {
            course.setTeacher(this.teacher.fromThis());
        }

        if (Objects.nonNull(this.courseStudents)) {
            course.setStudents(this.courseStudents.stream().map(CourseStudentData::fromThis).toList());
        }

        return course;
    }

    public Course getInfo() {
        Course course = Course
                .builder()
                .id(Identity.from(this.getId()))
                .name(this.name)
                .description(this.description)
                .backgroundImage(this.backgroundImage)
                .thumbnail(this.thumbnail)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .price(this.price)
                .level(this.level)
                .status(this.status)
                .grade(this.grade)
                .code(this.code)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();

        if (Objects.nonNull(this.subject)) {
            course.setSubject(this.subject.fromThis());
        }

        if (Objects.nonNull(this.teacher)) {
            course.setTeacher(this.teacher.fromThis());
        }

        return course;
    }

}

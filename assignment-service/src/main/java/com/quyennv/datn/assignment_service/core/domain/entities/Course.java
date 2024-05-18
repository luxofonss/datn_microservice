package com.quyennv.datn.assignment_service.core.domain.entities;

import com.quyennv.datn.courseservice.core.domain.entities.*;
import com.quyennv.datn.courseservice.core.domain.enums.CourseLevel;
import com.quyennv.datn.courseservice.core.domain.enums.CourseStatus;
import com.quyennv.datn.courseservice.core.domain.valueobject.Conversation;
import com.quyennv.datn.courseservice.core.domain.valueobject.User;
import com.quyennv.datn.courseservice.core.utils.FunctionHelper;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    Identity id;
    String name;
    String description;
    String backgroundImage;
    String thumbnail;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Long price;
    CourseLevel level;
    CourseStatus status;
    Integer grade;
    String code;

    List<Section> sections;
    List<CourseInfo> courseInfos;
    List<Conversation> conversations;
    Subject subject;
    User teacher;
    List<CourseStudent> students;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime deletedAt;

    public Course updateCourse(Course course) {
        BeanUtils.copyProperties(course, this, FunctionHelper.getNullPropertyNames(course,"courseInfos", "conversations", "teacher"));

        if (Objects.nonNull(course.getSections())) {
            this.updateSections(course.getSections());
        }

        if (Objects.nonNull(course.getCourseInfos())) {
            if (Objects.nonNull(this.courseInfos) && !this.courseInfos.isEmpty()) {
                List<CourseInfo> newCourseInfos = new ArrayList<>();
                course.getCourseInfos().forEach(newInfo -> {
                    boolean exists = false;
                    for (CourseInfo i : this.courseInfos) {
                        if (i.getId().equals(newInfo.getId())) {
                            BeanUtils.copyProperties(newInfo, i, FunctionHelper.getNullPropertyNames(i));
                            newCourseInfos.add(i);
                            exists = true;
                            break;
                        }
                    }

                    if (!exists) {
                        newCourseInfos.add(newInfo);
                    }
                });
                this.setCourseInfos(newCourseInfos);
            }
            else {
                this.setCourseInfos(course.getCourseInfos());
            }
        }

        return this;
    }

    public Course delete() {
        this.deletedAt = LocalDateTime.now();
        return this;
    }

    public Course addSections(List<Section> sections) {
        Set<Section> allSections = new HashSet<>(this.sections);
        allSections.addAll(sections);
        this.setSections(allSections.stream().toList());

        return this;
    }

    public Course updateSections(List<Section> sections) {

        this.setSections(this.sections.stream().map(section -> {
            for (Section s : sections ){
                if (section.getId().equals(s.getId())) {
                    section.update(s);
                    break;
                }
            }
            return section;
        }).toList());

        return this;
    }

    public Course deleteSections(List<Section> sections) {
        this.setSections(this.sections.stream().map(section -> {
            for (Section s : sections) {
                if (section.getId().equals(s.getId())) {
                    section.delete();
                    break;
                }
            }
            return section;
        }).toList());

        return this;
    }

    public Course addLessons(List<Lesson> lessons, Identity sectionId) {
        this.sections.forEach(section -> {
            if (section.getId().equals(sectionId)) {
                Set<Lesson> newLessons = new HashSet<>(section.getLessons());
                newLessons.addAll(lessons);
                section.setLessons(newLessons.stream().toList());
            }
        });

        return this;
    }

    public Course updateLessons(List<Lesson> lessons, Identity sectionId) {
        this.sections.forEach(section -> {
            if (section.getId().equals(sectionId)) {
                for(Lesson lesson : section.getLessons()) {
                    for(Lesson updateLesson : lessons) {
                        if (lesson.getId().equals(updateLesson.getId())) {
                            BeanUtils.copyProperties(
                                    updateLesson,
                                    lesson,
                                    FunctionHelper.getNullPropertyNames(updateLesson, "assignmentAttempt"));
                        }
                    }
                }
            }
        });

        return this;
    }

    public Course deleteLessons(List<Lesson> lessons, Identity sectionId) {
        this.sections.forEach(section -> {
            if (section.getId().equals(sectionId)) {
                for(Lesson lesson : section.getLessons()) {
                    for(Lesson updateLesson : lessons) {
                        if (lesson.getId().equals(updateLesson.getId())) {
                           lesson.delete();
                        }
                    }
                }
            }
        });

        return this;
    }
}

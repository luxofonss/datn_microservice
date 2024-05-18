package com.quyennv.datn.courseservice.presenter.dto.course;

import com.quyennv.datn.courseservice.core.domain.enums.CourseLevel;
import com.quyennv.datn.courseservice.presenter.config.annotations.ValueOfEnum;
import lombok.Value;

import java.util.List;

@Value
public class UpdateCourseRequest {
    String courseId;
    String name;
    String description;
    String backgroundImage;
    String thumbnail;
    String startDate;
    String endDate;
    Long price;
    @ValueOfEnum(enumClass = CourseLevel.class)
    String level;
    Integer grade;
    String subjectId;

    List<UpdateCourseRequestCourseInfo> courseInfos;
    List<UpdateCourseRequestSection> sections;
    List<UpdateCourseRequestCourseStudent> students;
    List<UpdateCourseRequestLesson> lessons;
}

package com.quyennv.datn.assignment_service.presenter.dto.assignment;

import lombok.Value;


@Value
public class GetAssignmentFilters {
    String title;
    String teacherId;
    String subjectId;
    String courseId;
    String lessonId;
    String studentId;
}

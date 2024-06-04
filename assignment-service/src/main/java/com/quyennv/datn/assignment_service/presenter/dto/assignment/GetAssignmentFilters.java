package com.quyennv.lms.presenter.rest.dto.assignment;

import lombok.Value;


@Value
public class GetAssignmentFilters {
    String title;
    String teacherId;
    String subjectId;
    String courseId;
    String sectionId;
    String studentId;
}

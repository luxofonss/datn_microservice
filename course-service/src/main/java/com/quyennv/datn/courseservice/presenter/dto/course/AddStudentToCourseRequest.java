package com.quyennv.datn.courseservice.presenter.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddStudentToCourseRequest {
    List<String> emails;
}

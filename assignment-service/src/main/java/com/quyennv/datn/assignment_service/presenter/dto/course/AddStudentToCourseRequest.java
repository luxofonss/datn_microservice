package com.quyennv.datn.assignment_service.presenter.dto.course;

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

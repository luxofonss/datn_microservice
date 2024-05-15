package com.quyennv.datn.courseservice.presenter.dto;

import jakarta.annotation.Nullable;
import lombok.Value;

@Value
public class ApiResponse {
    Boolean success;
    String message;
    @Nullable
    Object data;
}

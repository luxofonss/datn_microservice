package com.quyennv.datn.apigateway.dto;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
    Boolean success;
    String message;
    @Nullable
    T data;
}

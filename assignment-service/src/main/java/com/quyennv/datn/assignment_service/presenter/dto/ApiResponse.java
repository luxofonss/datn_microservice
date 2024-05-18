package com.quyennv.datn.assignment_service.presenter.dto;

import jakarta.annotation.Nullable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    Boolean success;
    String message;
    @Nullable
    T data;
}

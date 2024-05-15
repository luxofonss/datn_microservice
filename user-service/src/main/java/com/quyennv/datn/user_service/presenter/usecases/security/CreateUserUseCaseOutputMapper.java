package com.quyennv.datn.user_service.presenter.usecases.security;

import com.quyennv.datn.user_service.core.usecases.user.CreateUserUseCase;
import com.quyennv.datn.user_service.presenter.dto.ApiResponse;
import com.quyennv.datn.user_service.presenter.dto.auth.AuthRegisterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
public class CreateUserUseCaseOutputMapper {
    public static ResponseEntity<ApiResponse> map(CreateUserUseCase.OutputValues outputValues) {
        return ResponseEntity.ok(new ApiResponse(
                true,
                "Register successfully!",
                new AuthRegisterResponse(outputValues.getUserId()))
        );
    }
}

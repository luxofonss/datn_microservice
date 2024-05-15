package com.quyennv.datn.user_service.presenter.usecases.security;

import com.quyennv.datn.user_service.presenter.dto.ApiResponse;
import com.quyennv.datn.user_service.presenter.dto.auth.AuthLoginResponse;
import org.springframework.http.ResponseEntity;

public class AuthenticationUseCaseOutputMapper {
    public static ResponseEntity<ApiResponse> map(AuthenticationUseCase.OutputValues outputValues) {
        return ResponseEntity.ok(new ApiResponse(
                true,
                "Login successfully!",
                new AuthLoginResponse(
                outputValues.getAccessToken(), outputValues.getRefreshToken()))
        );
    }
}

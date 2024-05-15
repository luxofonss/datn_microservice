package com.quyennv.datn.user_service.presenter.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;


@Value
public class AuthLoginRequest {
    @NotBlank
    String username;
    @NotBlank
    String password;
}

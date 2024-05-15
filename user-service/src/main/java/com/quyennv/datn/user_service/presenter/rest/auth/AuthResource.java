package com.quyennv.datn.user_service.presenter.rest.auth;

import com.quyennv.datn.user_service.presenter.dto.ApiResponse;
import com.quyennv.datn.user_service.presenter.dto.auth.AuthLoginRequest;
import com.quyennv.datn.user_service.presenter.dto.auth.AuthRegisterRequest;
import com.quyennv.datn.user_service.presenter.usecases.security.CurrentUser;
import com.quyennv.datn.user_service.presenter.usecases.security.UserPrincipal;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/auth")
public interface AuthResource {
    @PostMapping("/login")
    CompletableFuture<ResponseEntity<ApiResponse>> login(@Valid @RequestBody AuthLoginRequest loginRequest);

    @PostMapping("/register")
    CompletableFuture<ResponseEntity<ApiResponse>> register(@Valid @RequestBody AuthRegisterRequest loginRequest);

    @GetMapping("/who-am-i")
    CompletableFuture<ResponseEntity<ApiResponse>> whoAmI(@CurrentUser UserPrincipal requester);
}

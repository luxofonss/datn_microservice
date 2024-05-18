package com.quyennv.datn.user_service.presenter.rest.user;

import com.quyennv.datn.user_service.presenter.dto.ApiResponse;
import com.quyennv.datn.user_service.presenter.usecases.security.CurrentUser;
import com.quyennv.datn.user_service.presenter.usecases.security.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping
public interface UserResource {
    @GetMapping
    CompletableFuture<ResponseEntity<ApiResponse>> getByConditions(
            @CurrentUser UserPrincipal requester,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) List<String> emails);
}
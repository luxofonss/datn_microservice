package com.quyennv.datn.communication_service.adapter.services.user_services;

import com.quyennv.datn.communication_service.presenter.dto.ApiResponse;
import com.quyennv.datn.communication_service.presenter.dto.user.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "user-service",
        url = "${app.services.user}")
public interface UserService {
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<List<UserResponse>> getAllUsers(
            @RequestHeader("Authorization") String authorization,
            @RequestParam List<String> emails);

}

package com.quyennv.datn.apigateway.repository;

import com.quyennv.datn.apigateway.dto.ApiResponse;
import com.quyennv.datn.apigateway.dto.IntrospectResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface IdentityClient {
    @GetExchange(url = "/users/auth/introspect")
    Mono<ApiResponse<IntrospectResponse>> introspect(
            @RequestHeader("Authorization") String token
    );
}

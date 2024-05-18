package com.quyennv.datn.apigateway.services;

import com.quyennv.datn.apigateway.dto.ApiResponse;
import com.quyennv.datn.apigateway.dto.IntrospectResponse;
import com.quyennv.datn.apigateway.repository.IdentityClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {
    IdentityClient identityClient;
    public Mono<ApiResponse<IntrospectResponse>> introspect(String token) {
        return identityClient.introspect(token);
    }
}

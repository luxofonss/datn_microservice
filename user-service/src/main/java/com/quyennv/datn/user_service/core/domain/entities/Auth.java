package com.quyennv.datn.user_service.core.domain.entities;

import com.quyennv.datn.user_service.core.domain.enums.AuthType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Auth {
    private Identity id;
    private Identity userId;
    private AuthType type;
    private String providerId;
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

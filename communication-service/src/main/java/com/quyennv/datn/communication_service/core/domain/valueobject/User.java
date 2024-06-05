package com.quyennv.datn.communication_service.core.domain.valueobject;

import com.quyennv.datn.communication_service.core.domain.entities.Identity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private Identity id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private String role;
    private String avatar;
    private LocalDateTime dateOfBirth;
    private Boolean isVerified;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}

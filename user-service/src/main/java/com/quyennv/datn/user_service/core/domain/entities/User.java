package com.quyennv.datn.user_service.core.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quyennv.datn.user_service.core.domain.enums.Gender;
import com.quyennv.datn.user_service.core.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

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
    private Gender gender;
    private Role role;
    private String avatar;
    private LocalDateTime dateOfBirth;
    private Boolean isVerified;
    private Address address;
    private LearnerInfo learnerInfo;
    private TeacherInfo teacherInfo;
    @JsonIgnore
    private Auth auth;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @JsonIgnore
    public String getPassword() {
        if (Objects.nonNull(auth)) {
            return auth.getPassword();
        } else {
            return null;
        }
    }
}

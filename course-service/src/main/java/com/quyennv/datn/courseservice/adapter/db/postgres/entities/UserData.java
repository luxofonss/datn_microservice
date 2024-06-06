package com.quyennv.datn.courseservice.adapter.db.postgres.entities;

import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.entities.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class UserData{
    @Id
    private UUID id;

    private String email;
    private String username;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    private String phoneNumber;
    private String gender;
    private String role;
    private String avatar;
    @Column(name="date_of_birth")
    private LocalDateTime dateOfBirth;
    @Column(name="is_verified")
    private Boolean isVerified;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public static UserData newWithId(Identity id) {
        UserData data = new UserData();
        data.setId(id.getUUID());
        return data;
    }

    public static UserData from(User u) {
        UserData result = UserData
                .builder()
                .email(u.getEmail())
                .username(u.getUsername())
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .phoneNumber(u.getPhoneNumber())
                .gender(u.getGender())
                .role(u.getRole())
                .avatar(u.getAvatar())
                .dateOfBirth(u.getDateOfBirth())
                .isVerified(u.getIsVerified()).build();
        if (Objects.nonNull(u.getId())) {
            result.setId(u.getId().getId());
        }
        result.setCreatedAt(u.getCreatedAt());
        result.setUpdatedAt(u.getUpdatedAt());
        result.setDeletedAt(u.getDeletedAt());

        log.info("result::{} ", result);
        return result;
    }

    public User fromThis() {
        return User
                .builder()
                .id(Identity.from(this.getId()))
                .email(this.email)
                .username(this.username)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .phoneNumber(this.phoneNumber)
                .gender(this.gender)
                .role(this.role)
                .avatar(this.avatar)
                .dateOfBirth(this.dateOfBirth)
                .isVerified(this.isVerified)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}

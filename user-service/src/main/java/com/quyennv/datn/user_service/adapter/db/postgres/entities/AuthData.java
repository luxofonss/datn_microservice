package com.quyennv.datn.user_service.adapter.db.postgres.entities;

import com.quyennv.datn.user_service.core.domain.entities.Auth;
import com.quyennv.datn.user_service.core.domain.entities.Identity;
import com.quyennv.datn.user_service.core.domain.enums.AuthType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity(name="auths")
@Getter
@Setter
@ToString(exclude = {"user"})
@Table(name="auths")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthData extends BaseEntity {
    @Column(name="auth_type")
    @Enumerated(EnumType.STRING)
    private AuthType type;
    @Column(name="service_id")
    private String providerId;
    private String password;
    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    private UserData user;

    public static AuthData from(Auth auth) {
        AuthData result = AuthData.builder()
                .type(auth.getType())
                .providerId(auth.getProviderId())
                .password(auth.getPassword())
                .build();

        if (Objects.nonNull(auth.getId())) {
            result.setId(auth.getId().getId());
        }
        result.setCreatedAt(auth.getCreatedAt());
        result.setUpdatedAt(auth.getUpdatedAt());
        result.setDeletedAt(auth.getDeletedAt());

        return result;
    }

    public Auth fromThis() {
        return Auth.builder()
                .id(Identity.from(this.getId()))
                .type(this.type)
                .providerId(this.providerId)
                .password(this.password)
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .deletedAt(this.getDeletedAt())
                .build();
    }
}

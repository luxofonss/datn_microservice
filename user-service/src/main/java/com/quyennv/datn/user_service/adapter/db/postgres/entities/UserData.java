package com.quyennv.datn.user_service.adapter.db.postgres.entities;
import com.quyennv.datn.user_service.core.domain.entities.Identity;
import com.quyennv.datn.user_service.core.domain.entities.User;
import com.quyennv.datn.user_service.core.domain.enums.Gender;
import com.quyennv.datn.user_service.core.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name="users")
@Getter
@Setter
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class UserData extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name="phone_number", unique = true, nullable = false)
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String avatar;
    @Column(name="dob")
    private LocalDateTime dateOfBirth;
    @Column(name="verified")
    private Boolean isVerified;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private TeacherInfoData teacherInfo;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private LearnerInfoData learnerInfo;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AddressData address;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AuthData auth;

    public User fromThis() {
        User user = User.builder()
                .id(Identity.from(this.getId()))
                .email(this.email)
                .username(this.username)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .avatar(this.avatar)
                .phoneNumber(this.phoneNumber)
                .gender(this.gender)
                .role(this.role)
                .dateOfBirth(this.dateOfBirth)
                .isVerified(this.isVerified)
                .build();

        if (Objects.nonNull(this.address)) {
            user.setAddress(this.address.fromThis());
        }

        if (Objects.nonNull(this.getLearnerInfo())) {
            user.setLearnerInfo(this.getLearnerInfo().fromThis());
        }

        if (Objects.nonNull(this.getTeacherInfo())) {
            user.setTeacherInfo(this.getTeacherInfo().fromThis());
        }

        if (Objects.nonNull(this.auth)) {
            user.setAuth(this.auth.fromThis());
        }

        return user;
    }

    public static UserData from(User user) {
        UserData userData =  UserData
                .builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender())
                .role(user.getRole())
                .avatar(user.getAvatar())
                .dateOfBirth(user.getDateOfBirth())
                .isVerified(user.getIsVerified())
                .build();

        if (Objects.nonNull(user.getId())) {
            userData.setId(user.getId().getId());
        }

        if (Objects.nonNull(user.getTeacherInfo())) {
            userData.setTeacherInfo(TeacherInfoData.from(user.getTeacherInfo()));
            userData.teacherInfo.setUser(userData);
        }

        if (Objects.nonNull(user.getLearnerInfo())) {
            userData.setLearnerInfo(LearnerInfoData.from(user.getLearnerInfo()));
            userData.learnerInfo.setUser(userData);
        }

        if (Objects.nonNull(user.getAddress())) {
            userData.setAddress(AddressData.from(user.getAddress()));
            userData.address.setUser(userData);

        }

        if (Objects.nonNull(user.getAuth())) {
            AuthData auth = AuthData.from(user.getAuth());
            auth.setUser(userData);
            userData.setAuth(auth);
        }

        return userData;
    }
}

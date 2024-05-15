package com.quyennv.datn.user_service.core.usecases.user;

import com.quyennv.datn.user_service.core.domain.entities.*;
import com.quyennv.datn.user_service.core.domain.enums.AuthType;
import com.quyennv.datn.user_service.core.domain.enums.Gender;
import com.quyennv.datn.user_service.core.domain.enums.Role;
import com.quyennv.datn.user_service.core.exceptions.EmailUsernameOrPhoneAlreadyUsedException;
import com.quyennv.datn.user_service.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

public class CreateUserUseCase extends UseCase<CreateUserUseCase.InputValues, CreateUserUseCase.OutputValues> {
    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        User user = userRepository.findByEmailOrUsernameOrPhoneNumber(input.getUsername(), input.getEmail(), input.getPhoneNumber()).orElse(null);

        if (user != null) {
            throw new EmailUsernameOrPhoneAlreadyUsedException("User already exists");
        }
        return new OutputValues(
            userRepository.persist(createUser(input)).getId().getId().toString()
        );
    }

    private User createUser(InputValues input) {
        User user = User
                .builder()
                .id(Identity.newIdentity())
                .username(input.getUsername())
                .email(input.getEmail())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .phoneNumber(input.getPhoneNumber())
                .gender(input.getGender())
                .dateOfBirth(input.getDateOfBirth())
                .avatar(input.getAvatar())
                .role(input.getRole())
                .learnerInfo(input.getLearnerInfo())
                .teacherInfo(input.getTeacherInfo())
                .build();

        Auth auth = Auth
                .builder()
                .userId(user.getId())
                .type(input.getAuthType())
                .password(input.getPassword())
                .build();

        user.setAuth(auth);

        return user;
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        String username;
        String email;
        String password;
        String firstName;
        String lastName;
        String phoneNumber;
        Gender gender;
        LocalDateTime dateOfBirth;
        String avatar;
        AuthType authType;
        Role role;
        LearnerInfo learnerInfo;
        TeacherInfo teacherInfo;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        String userId;
    }
}

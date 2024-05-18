package com.quyennv.datn.user_service.core.usecases.user;

import com.quyennv.datn.user_service.core.domain.entities.Identity;
import com.quyennv.datn.user_service.core.domain.entities.User;
import com.quyennv.datn.user_service.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

import java.util.List;

public abstract class GetUsersUseCase extends UseCase<
        GetUsersUseCase.InputValues, GetUsersUseCase.OutputValues> {
    public final UserRepository userRepository;

    protected GetUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(getUsers(input));
    }

    public abstract List<User> getUsers(InputValues input);

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        List<String> emails;
        List<Identity> userIds;
        String keyword;
        Identity requesterId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        List<User> users;
    }
}

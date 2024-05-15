package com.quyennv.datn.user_service.core.usecases.user;

import com.quyennv.datn.user_service.core.domain.entities.User;
import com.quyennv.datn.user_service.core.usecases.UseCase;
import lombok.Value;

public class GetAuthProfileUseCase extends UseCase<GetAuthProfileUseCase.InputValues, GetAuthProfileUseCase.OutputValues> {
    private final UserRepository userRepository;

    public GetAuthProfileUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        User user = userRepository.getUserById(input.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        return new OutputValues(user);
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        String userId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        User user;
    }
}

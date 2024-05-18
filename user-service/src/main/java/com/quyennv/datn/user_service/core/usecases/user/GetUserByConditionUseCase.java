package com.quyennv.datn.user_service.core.usecases.user;

import com.quyennv.datn.user_service.core.domain.entities.User;

import java.util.List;

public class GetUserByConditionUseCase extends GetUsersUseCase{
    public GetUserByConditionUseCase(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public List<User> getUsers(InputValues input) {
        return userRepository.getUsersWithConditions(
                input.getEmails(),
                input.getUserIds(),
                input.getKeyword());
    }
}

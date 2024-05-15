package com.quyennv.datn.user_service.presenter.mapper.auth;

import com.quyennv.datn.user_service.core.usecases.user.GetAuthProfileUseCase;
import com.quyennv.datn.user_service.presenter.usecases.security.UserPrincipal;

public class GetAuthProfileUseCaseInputMapper {
    public static GetAuthProfileUseCase.InputValues map(UserPrincipal requester) {
        return new GetAuthProfileUseCase.InputValues(requester.getId().toString());
    }
}

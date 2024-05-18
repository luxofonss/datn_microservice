package com.quyennv.datn.user_service.presenter.rest.user;

import com.quyennv.datn.user_service.core.domain.entities.Identity;
import com.quyennv.datn.user_service.core.usecases.UseCaseExecutor;
import com.quyennv.datn.user_service.core.usecases.user.GetUserByConditionUseCase;
import com.quyennv.datn.user_service.presenter.dto.ApiResponse;
import com.quyennv.datn.user_service.presenter.usecases.security.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
public class UserController implements UserResource {
    private final UseCaseExecutor useCaseExecutor;
    private final GetUserByConditionUseCase getUserByConditionUseCase;

    public UserController(UseCaseExecutor useCaseExecutor, GetUserByConditionUseCase getUserByConditionUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.getUserByConditionUseCase = getUserByConditionUseCase;
    }

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> getByConditions(
            UserPrincipal requester,
            String keyword,
            List<String> emails) {
        return useCaseExecutor.execute(
                getUserByConditionUseCase,
                GetUserByConditionUseCase.InputValues.builder()
                        .keyword(keyword)
                        .emails(emails)
                        .requesterId(Identity.from(requester.getId()))
                        .build(),
                (outputValues) -> ResponseEntity.ok(new ApiResponse(true, "ok", outputValues.getUsers()))
        );
    }
}

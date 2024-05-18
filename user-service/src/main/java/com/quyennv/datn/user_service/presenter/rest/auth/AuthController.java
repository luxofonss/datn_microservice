package com.quyennv.datn.user_service.presenter.rest.auth;

import com.quyennv.datn.user_service.core.usecases.UseCaseExecutor;
import com.quyennv.datn.user_service.core.usecases.user.CreateUserUseCase;
import com.quyennv.datn.user_service.core.usecases.user.GetAuthProfileUseCase;
import com.quyennv.datn.user_service.presenter.dto.ApiResponse;
import com.quyennv.datn.user_service.presenter.dto.auth.AuthLoginRequest;
import com.quyennv.datn.user_service.presenter.dto.auth.AuthRegisterRequest;
import com.quyennv.datn.user_service.presenter.mapper.auth.GetAuthProfileUseCaseInputMapper;
import com.quyennv.datn.user_service.presenter.mapper.auth.GetAuthProfileUseCaseOutputMapper;
import com.quyennv.datn.user_service.presenter.usecases.security.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.concurrent.CompletableFuture;

@Controller
public class AuthController implements AuthResource{
    private final UseCaseExecutor useCaseExecutor;
    private final CreateUserUseCase createUserUseCase;
    private final AuthenticationUseCase authenticationUseCase;
    private final GetAuthProfileUseCase getAuthProfileUseCase;

    // MAPPERS
    private final CreateUserUseCaseInputMapper createUserUseCaseInputMapper;

    public AuthController(UseCaseExecutor useCaseExecutor,
                          CreateUserUseCase createUserUseCase,
                          AuthenticationUseCase authenticationUseCase,
                          GetAuthProfileUseCase getAuthProfileUseCase,
                          CreateUserUseCaseInputMapper createUserUseCaseInputMapper) {
        this.useCaseExecutor = useCaseExecutor;
        this.createUserUseCase = createUserUseCase;
        this.authenticationUseCase = authenticationUseCase;
        this.getAuthProfileUseCase = getAuthProfileUseCase;
        this.createUserUseCaseInputMapper = createUserUseCaseInputMapper;
    }

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> login(AuthLoginRequest loginRequest) {
        return useCaseExecutor.execute(
                authenticationUseCase,
                AuthenticationUseCaseInputMapper.map(loginRequest),
                AuthenticationUseCaseOutputMapper::map
        );
    }

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> register(AuthRegisterRequest loginRequest) {
        return useCaseExecutor.execute(
                createUserUseCase,
                createUserUseCaseInputMapper.map(loginRequest),
                CreateUserUseCaseOutputMapper::map
        );
    }

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> whoAmI(UserPrincipal requester) {
        return useCaseExecutor.execute(
                getAuthProfileUseCase,
                GetAuthProfileUseCaseInputMapper.map(requester),
                o -> GetAuthProfileUseCaseOutputMapper.map(o.getUser())
        );
    }

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> introspect(UserPrincipal requester) {
        return useCaseExecutor.execute(
                getAuthProfileUseCase,
                GetAuthProfileUseCaseInputMapper.map(requester),
                o -> GetAuthProfileUseCaseOutputMapper.map(o.getUser())
        );
    }
}

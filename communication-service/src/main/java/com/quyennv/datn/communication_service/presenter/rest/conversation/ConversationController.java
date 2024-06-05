package com.quyennv.datn.communication_service.presenter.rest.conversation;

import com.quyennv.datn.communication_service.core.domain.entities.Identity;
import com.quyennv.datn.communication_service.core.usecases.UseCaseExecutor;
import com.quyennv.datn.communication_service.core.usecases.conversation.CreateConversationUseCase;
import com.quyennv.datn.communication_service.core.usecases.conversation.DeleteConversationUseCase;
import com.quyennv.datn.communication_service.core.usecases.conversation.UpdateConversationInfoUseCase;
import com.quyennv.datn.communication_service.core.usecases.conversation.UpdateConversationUseCase;
import com.quyennv.datn.communication_service.presenter.dto.ApiResponse;
import com.quyennv.datn.communication_service.presenter.dto.conversation.CreateConversationRequest;
import com.quyennv.datn.communication_service.presenter.dto.conversation.UpdateConversationRequest;
import com.quyennv.datn.communication_service.presenter.mapper.conversation.CreateConversationUseCaseInputMapper;
import com.quyennv.datn.communication_service.presenter.mapper.conversation.UpdateConversationUseCaseInputMapper;
import com.quyennv.datn.communication_service.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

import java.util.concurrent.CompletableFuture;

@Controller
public class ConversationController implements ConversationResource {
    private final UseCaseExecutor useCaseExecutor;
    private final CreateConversationUseCase createConversationUseCase;
    private final UpdateConversationInfoUseCase updateConversationInfoUseCase;
    private final DeleteConversationUseCase deleteConversationUseCase;

    public ConversationController(UseCaseExecutor useCaseExecutor,
                                  CreateConversationUseCase createConversationUseCase,
                                  UpdateConversationInfoUseCase updateConversationInfoUseCase,
                                  DeleteConversationUseCase deleteConversationUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.createConversationUseCase = createConversationUseCase;
        this.updateConversationInfoUseCase = updateConversationInfoUseCase;
        this.deleteConversationUseCase = deleteConversationUseCase;
    }

    @Override
    public CompletableFuture<ApiResponse> createConversation(
            CreateConversationRequest request,
            UserPrincipal requester,
            HttpServletRequest servletRequest) {
        return useCaseExecutor.execute(
                createConversationUseCase,
                CreateConversationUseCaseInputMapper.map(request, requester),
                outputValues -> new ApiResponse(true, "ok", outputValues.getConversation())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> updateConversation(
            String id,
            UpdateConversationRequest request,
            UserPrincipal requester,
            HttpServletRequest servletRequest) {
        return useCaseExecutor.execute(
                updateConversationInfoUseCase,
                UpdateConversationUseCaseInputMapper.map(id, request, requester),
                outputValues -> new ApiResponse(true, "ok", outputValues.getConversation())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> deleteConversation(
            String id,
            UserPrincipal requester,
            HttpServletRequest servletRequest) {
        return useCaseExecutor.execute(
                deleteConversationUseCase,
                UpdateConversationUseCase.InputValues.builder().id(Identity.fromString(id)).build(),
                outputValues -> new ApiResponse(true, "ok", null)
        );
    }
}

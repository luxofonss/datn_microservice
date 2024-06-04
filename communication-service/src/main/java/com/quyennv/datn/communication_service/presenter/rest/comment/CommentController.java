package com.quyennv.lms.presenter.rest.api.comment;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.usecases.UseCaseExecutor;
import com.quyennv.lms.core.usecases.comment.CreateCommentUseCase;
import com.quyennv.lms.core.usecases.comment.DeleteCommentUseCase;
import com.quyennv.lms.core.usecases.comment.UpdateCommentInfoUseCase;
import com.quyennv.lms.core.usecases.comment.UpdateCommentUseCase;
import com.quyennv.lms.presenter.rest.dto.ApiResponse;
import com.quyennv.lms.presenter.rest.dto.CreateCommentRequest;
import com.quyennv.lms.presenter.rest.dto.UpdateCommentRequest;
import com.quyennv.lms.presenter.rest.mapper.comment.CreateCommentUseCaseInputMapper;
import com.quyennv.lms.presenter.rest.mapper.comment.UpdateCommentInputMapper;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

import java.util.concurrent.CompletableFuture;

@Controller
public class CommentController implements CommentResource{
    private final UseCaseExecutor useCaseExecutor;
    private final CreateCommentUseCase createCommentUseCase;
    private final UpdateCommentInfoUseCase updateCommentInfoUseCase;
    private final DeleteCommentUseCase deleteCommentUseCase;

    public CommentController(UseCaseExecutor useCaseExecutor,
                             CreateCommentUseCase createCommentUseCase,
                             UpdateCommentInfoUseCase updateCommentInfoUseCase,
                             DeleteCommentUseCase deleteCommentUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.createCommentUseCase = createCommentUseCase;
        this.updateCommentInfoUseCase = updateCommentInfoUseCase;
        this.deleteCommentUseCase = deleteCommentUseCase;
    }

    @Override
    public CompletableFuture<ApiResponse> createComment(
            CreateCommentRequest request,
            UserPrincipal requester,
            HttpServletRequest servletRequest) {
        return useCaseExecutor.execute(
                createCommentUseCase,
                CreateCommentUseCaseInputMapper.map(request, requester),
                (outputValues) -> new ApiResponse(true, "ok", outputValues.getComment())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> updateComment(
            String id,
            UpdateCommentRequest request,
            UserPrincipal requester,
            HttpServletRequest servletRequest) {
        return useCaseExecutor.execute(
                updateCommentInfoUseCase,
                UpdateCommentInputMapper.map(id, request, requester),
                (outputValues) -> new ApiResponse(true, "ok", outputValues.getComment())

        );
    }

    @Override
    public CompletableFuture<ApiResponse> deleteComment(
            String id,
            UserPrincipal requester,
            HttpServletRequest servletRequest) {
        return useCaseExecutor.execute(
                deleteCommentUseCase,
                UpdateCommentUseCase.InputValues
                        .builder()
                        .id(Identity.fromString(id))
                        .requesterId(Identity.from(requester.getId()))
                        .build(),
                (outputValues) -> new ApiResponse(true, "ok", null)
        );
    }
}

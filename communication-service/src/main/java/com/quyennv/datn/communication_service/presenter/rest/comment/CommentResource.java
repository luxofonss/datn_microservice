package com.quyennv.datn.communication_service.presenter.rest.comment;

import com.quyennv.datn.communication_service.presenter.dto.ApiResponse;
import com.quyennv.datn.communication_service.presenter.dto.comment.CreateCommentRequest;
import com.quyennv.datn.communication_service.presenter.dto.comment.UpdateCommentRequest;
import com.quyennv.datn.communication_service.presenter.usecases.security.CurrentUser;
import com.quyennv.datn.communication_service.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/comments")
public interface CommentResource {
    @PostMapping
    CompletableFuture<ApiResponse> createComment(
            @RequestBody CreateCommentRequest request,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest servletRequest
    );

    @PutMapping("/{id}")
    CompletableFuture<ApiResponse> updateComment(
            @PathVariable String id,
            @RequestBody UpdateCommentRequest request,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest servletRequest
    );

    @DeleteMapping("/{id}")
    CompletableFuture<ApiResponse> deleteComment(
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest servletRequest
    );
}

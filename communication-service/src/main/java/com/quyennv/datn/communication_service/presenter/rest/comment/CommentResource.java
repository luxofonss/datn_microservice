package com.quyennv.lms.presenter.rest.api.comment;

import com.quyennv.lms.presenter.rest.dto.ApiResponse;
import com.quyennv.lms.presenter.rest.dto.CreateCommentRequest;
import com.quyennv.lms.presenter.rest.dto.UpdateCommentRequest;
import com.quyennv.lms.presenter.usecases.security.CurrentUser;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/comments")
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

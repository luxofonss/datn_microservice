package com.quyennv.lms.presenter.rest.api.assignment;

import com.quyennv.lms.presenter.rest.dto.ApiResponse;
import com.quyennv.lms.presenter.rest.dto.assignment.*;
import com.quyennv.lms.presenter.usecases.security.CurrentUser;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/assignments")
public interface AssignmentResource {
    @PostMapping
    CompletableFuture<ApiResponse> create(
            @Valid @RequestBody CreateAssignmentRequest req,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @GetMapping
    CompletableFuture<ApiResponse> getWithCondition(
            @Valid @ModelAttribute GetAssignmentFilters filters,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );
    @GetMapping("/{id}")
    CompletableFuture<ApiResponse> getOne(
            @PathVariable @NotNull String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );


    @PutMapping("/{id}")
    CompletableFuture<ApiResponse> update(
            @Valid @RequestBody UpdateAssignmentRequest req,
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PostMapping("/{id}/attempts")
    CompletableFuture<ApiResponse> attempt(
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PostMapping("/attempts/{attemptId}/questions/{questionId}")
    CompletableFuture<ApiResponse> submitQuestionAnswer(
            @PathVariable String attemptId,
            @PathVariable String questionId,
            @RequestBody SubmitQuestionAnswerRequest req,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PostMapping("/attempts/{attemptId}/questions/{questionId}/score")
    CompletableFuture<ApiResponse> scoreQuestionAnswer(
            @PathVariable String attemptId,
            @PathVariable String questionId,
            @RequestBody TeacherScoreAnswerRequest req,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );
    @GetMapping("/attempts/{attemptId}")
    CompletableFuture<ApiResponse> getOneAssignmentAttempt(
            @PathVariable String attemptId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );
    @PostMapping("/attempts/{attemptId}/submit")
    CompletableFuture<ApiResponse> submitAssignment(
            @PathVariable String attemptId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PutMapping("/attempts/{attemptId}/answers/{answerId}/fix-answer")
    CompletableFuture<ApiResponse> teacherFixQuestionAnswer(
            @PathVariable String attemptId,
            @PathVariable String answerId,
            @RequestBody TeacherFixQuestionAnswerRequest req,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PostMapping("/attempts/{attemptId}/answers/{answerId}/feedbacks")
    CompletableFuture<ApiResponse> feedbackQuestionAnswer(
            @PathVariable String attemptId,
            @PathVariable String answerId,
            @RequestBody FeedbackAnswerRequest req,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PutMapping("/attempts/{attemptId}/answers/{answerId}/feedbacks/{feedbackId}")
    CompletableFuture<ApiResponse> updateFeedbackQuestionAnswer(
            @PathVariable String attemptId,
            @PathVariable String answerId,
            @PathVariable String feedbackId,
            @RequestBody FeedbackAnswerRequest req,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @DeleteMapping("/attempts/{attemptId}/answers/{answerId}/feedbacks/{feedbackId}")
    CompletableFuture<ApiResponse> deleteFeedbackQuestionAnswer(
            @PathVariable String attemptId,
            @PathVariable String answerId,
            @PathVariable String feedbackId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PostMapping("/{id}/questions")
    CompletableFuture<ApiResponse> addQuestions(
            @Valid @RequestBody UpdateAssignmentRequest req,
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );
}

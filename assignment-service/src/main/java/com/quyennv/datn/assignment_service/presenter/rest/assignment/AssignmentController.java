package com.quyennv.datn.assignment_service.presenter.rest.assignment;

import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.usecases.UseCaseExecutor;
import com.quyennv.datn.assignment_service.core.usecases.answer_feedback.*;
import com.quyennv.datn.assignment_service.core.usecases.assignment.*;
import com.quyennv.datn.assignment_service.core.usecases.assignment_attempt.AttemptAssignmentUseCase;
import com.quyennv.datn.assignment_service.core.usecases.assignment_attempt.GetAssignmentAttemptByIdUseCase;
import com.quyennv.datn.assignment_service.core.usecases.assignment_attempt.GetAttemptsByAssignmentId;
import com.quyennv.datn.assignment_service.core.usecases.assignment_attempt.SubmitQuestionAnswerUseCase;
import com.quyennv.datn.assignment_service.core.usecases.question.AddQuestionsToAssignmentUseCase;
import com.quyennv.datn.assignment_service.presenter.dto.ApiResponse;
import com.quyennv.datn.assignment_service.presenter.dto.assignment.*;
import com.quyennv.datn.assignment_service.presenter.mapper.assignment.*;
import com.quyennv.datn.assignment_service.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Controller
public class AssignmentController implements AssignmentResource{
    private final UseCaseExecutor useCaseExecutor;
    private final CreateAssignmentUseCase createAssignmentUseCase;
    private final UpdateAssignmentDetailUseCase updateAssignmentDetailUseCase;
    private final AddQuestionsToAssignmentUseCase addQuestionsToAssignmentUseCase;
    private final GetOneAssignmentUseCase getOneAssignmentUseCase;
    private final AttemptAssignmentUseCase attemptAssignmentUseCase;
    private final SubmitQuestionAnswerUseCase submitQuestionAnswerUseCase;
    private final SubmitAssignmentUseCase submitAssignmentUseCase;
    private final GetAssignmentInCourseUseCase getAssignmentInCourseUseCase;
    private final GetAssignmentAttemptByIdUseCase getAssignmentAttemptByIdUseCase;
    private final TeacherScoreAnswerUseCase teacherScoreAnswerUseCase;
    private final TeacherAddQuestionFeedBackUseCase teacherAddQuestionFeedBackUseCase;
    private final UpdateQuestionAnswerFeedbackDataUseCase updateQuestionAnswerFeedbackUseCase;
    private final DeleteQuestionAnswerFeedbackUseCase deleteQuestionAnswerFeedbackUseCase;
    private final TeacherFixLongAnswerUseCase teacherFixLongAnswerUseCase;
    private final GetAssignmentsByCourseUseCase getAssignmentsByCourseUseCase;

    private final GetAttemptsByAssignmentId getAttemptsByAssignmentId;

    public AssignmentController(UseCaseExecutor useCaseExecutor,
                                CreateAssignmentUseCase createAssignmentUseCase,
                                UpdateAssignmentDetailUseCase updateAssignmentDetailUseCase,
                                AddQuestionsToAssignmentUseCase addQuestionsToAssignmentUseCase,
                                GetOneAssignmentUseCase getOneAssignmentUseCase,
                                AttemptAssignmentUseCase attemptAssignmentUseCase,
                                SubmitQuestionAnswerUseCase submitQuestionAnswerUseCase,
                                SubmitAssignmentUseCase submitAssignmentUseCase,
                                GetAssignmentInCourseUseCase getAssignmentInCourseUseCase,
                                GetAssignmentAttemptByIdUseCase getAssignmentAttemptByIdUseCase,
                                TeacherScoreAnswerUseCase teacherScoreAnswerUseCase,
                                TeacherAddQuestionFeedBackUseCase teacherAddQuestionFeedBackUseCase,
                                UpdateQuestionAnswerFeedbackDataUseCase updateQuestionAnswerFeedbackUseCase,
                                DeleteQuestionAnswerFeedbackUseCase deleteQuestionAnswerFeedbackUseCase,
                                TeacherFixLongAnswerUseCase teacherFixLongAnswerUseCase,
                                GetAssignmentsByCourseUseCase getAssignmentsByCourseUseCase,
                                GetAttemptsByAssignmentId getAttemptsByAssignmentId) {
        this.useCaseExecutor = useCaseExecutor;
        this.createAssignmentUseCase = createAssignmentUseCase;
        this.updateAssignmentDetailUseCase = updateAssignmentDetailUseCase;
        this.addQuestionsToAssignmentUseCase = addQuestionsToAssignmentUseCase;
        this.getOneAssignmentUseCase = getOneAssignmentUseCase;
        this.attemptAssignmentUseCase = attemptAssignmentUseCase;
        this.submitQuestionAnswerUseCase = submitQuestionAnswerUseCase;
        this.submitAssignmentUseCase = submitAssignmentUseCase;
        this.getAssignmentInCourseUseCase = getAssignmentInCourseUseCase;
        this.getAssignmentAttemptByIdUseCase = getAssignmentAttemptByIdUseCase;
        this.teacherScoreAnswerUseCase = teacherScoreAnswerUseCase;
        this.teacherAddQuestionFeedBackUseCase = teacherAddQuestionFeedBackUseCase;
        this.updateQuestionAnswerFeedbackUseCase = updateQuestionAnswerFeedbackUseCase;
        this.deleteQuestionAnswerFeedbackUseCase = deleteQuestionAnswerFeedbackUseCase;
        this.teacherFixLongAnswerUseCase = teacherFixLongAnswerUseCase;
        this.getAssignmentsByCourseUseCase = getAssignmentsByCourseUseCase;
        this.getAttemptsByAssignmentId = getAttemptsByAssignmentId;
    }

    @Override
    public CompletableFuture<ApiResponse> create(
            CreateAssignmentRequest req,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                createAssignmentUseCase,
                CreateAssignmentUseCaseInputMapper.map(requester, req),
                outputValues -> new ApiResponse(true, "ok", outputValues.getAssignment())
        );
    }

//    @Override
//    public CompletableFuture<ApiResponse> getWithCondition
//            (GetAssignmentFilters filters,
//             UserPrincipal requester,
//             HttpServletRequest httpServletRequest) {
//        return useCaseExecutor.execute(
//                getAssignmentInCourseUseCase,
//                GetAssignmentsFilterMapper.map(filters, requester),
//                outputValues -> new ApiResponse(true, "ok", outputValues.getAssignments())
//
//        );
//    }

    @Override
    public CompletableFuture<ApiResponse> getOne(
            String id,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                getOneAssignmentUseCase,
                GetOneAssignmentUseCase.InputValues
                        .builder()
                        .assignmentId(Identity.fromString(id))
                        .requesterId(Identity.from(requester.getId()))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getAssignment())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> getByCourse(String courseId, UserPrincipal requester, HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                getAssignmentsByCourseUseCase,
                GetAssignmentsByCourseUseCase.InputValues
                        .builder()
                        .requesterId(Identity.from(requester.getId()))
                        .courseId(Identity.fromString(courseId))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getAssignments())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> update(
            UpdateAssignmentRequest req,
            String id,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                updateAssignmentDetailUseCase,
                UpdateAssignmentDetailUseCaseRequestMapper.map(requester, req, id),
                outputValues -> new ApiResponse(true, "ok", outputValues.getAssignment())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> attempt(String id, UserPrincipal requester, HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                attemptAssignmentUseCase,
                AttemptAssignmentUseCase.InputValues
                        .builder()
                        .assignmentId(Identity.fromString(id))
                        .studentId(Identity.from(requester.getId()))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getAttempt())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> submitQuestionAnswer(
            String attemptId,
            String questionId,
            SubmitQuestionAnswerRequest req,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                submitQuestionAnswerUseCase,
                SubmitQuestionAnswerUseCase.InputValues
                        .builder()
                        .assignmentAttemptId(Identity.fromString(attemptId))
                        .questionId(Identity.fromString(questionId))
                        .selectedAnswerIds(Objects.nonNull(req.getSelectedOptionIds())
                                ? req.getSelectedOptionIds().stream().map(Identity::fromString).toList()
                                : null)
                        .textAnswer(req.getTextAnswer())
                        .requesterId(Identity.from(requester.getId()))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getQuestionAnswer())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> scoreQuestionAnswer(
            String attemptId,
            String questionId,
            TeacherScoreAnswerRequest req,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                teacherScoreAnswerUseCase,
                TeacherScoreAnswerUseCase.InputValues
                        .builder()
                        .attemptId(Identity.fromString(attemptId))
                        .questionId(Identity.fromString(questionId))
                        .score(req.getScore())
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getQuestionAnswer())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> getOneAssignmentAttempt(
            String attemptId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                getAssignmentAttemptByIdUseCase,
                new GetAssignmentAttemptByIdUseCase.InputValues(Identity.fromString(attemptId)),
                outputValues -> new ApiResponse(true, "ok", outputValues.getAttempt())

        );
    }

    @Override
    public CompletableFuture<ApiResponse> getAttemptsByAssignmentId(String assignmentId, UserPrincipal requester, HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                getAttemptsByAssignmentId,
                new GetAttemptsByAssignmentId.InputValues(Identity.fromString(assignmentId), Identity.from(requester.getId())),
                outputValues -> new ApiResponse(true, "ok", outputValues.getAttempt())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> submitAssignment(
            String attemptId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                submitAssignmentUseCase,
                SubmitAssignmentUseCase.InputValues
                        .builder()
                        .assignmentAttemptId(Identity.fromString(attemptId))
                        .requesterId(Identity.from(requester.getId()))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getAssignmentAttempt())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> teacherFixQuestionAnswer(
            String attemptId,
            String answerId,
            TeacherFixQuestionAnswerRequest req,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                teacherFixLongAnswerUseCase,
                TeacherFixLongAnswerUseCase.InputValues.builder()
                        .questionAnswerId(Identity.fromString(answerId))
                        .content(req.getContent())
                        .requesterId(Identity.fromString(requester.getId().toString()))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getAnswer())

        );
    }

    @Override
    public CompletableFuture<ApiResponse> feedbackQuestionAnswer
            (String attemptId,
             String answerId,
             FeedbackAnswerRequest req,
             UserPrincipal requester,
             HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                teacherAddQuestionFeedBackUseCase,
                AddAnswerFeedbackInputMapper.createInput(
                        req,
                        requester.getId().toString(),
                        answerId
                ),
                outputValues -> new ApiResponse(true, "ok", outputValues.getFeedback())

        );
    }

    @Override
    public CompletableFuture<ApiResponse> updateFeedbackQuestionAnswer(
            String attemptId,
            String answerId,
            String feedbackId,
            FeedbackAnswerRequest req,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                updateQuestionAnswerFeedbackUseCase,
                UpdateAnswerFeedbackInputMapper.map(
                        feedbackId,
                        req,
                        requester.getId().toString()
                ),
                outputValues -> new ApiResponse(true, "ok", outputValues.getFeedback())

        );
    }

    @Override
    public CompletableFuture<ApiResponse> deleteFeedbackQuestionAnswer(
            String attemptId,
            String answerId,
            String feedbackId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                deleteQuestionAnswerFeedbackUseCase,
                UpdateAnswerFeedbackInputMapper.map(
                        feedbackId,
                        FeedbackAnswerRequest.builder().build(),
                        requester.getId().toString()
                ),
                outputValues -> new ApiResponse(true, "ok", outputValues.getFeedback())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> addQuestions(
            UpdateAssignmentRequest req,
            String id,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                addQuestionsToAssignmentUseCase,
                AddQuestionToAssignmentInputMapper.map(requester, req, id),
                outputValues -> new ApiResponse(true, "ok", outputValues.getAssignment())
        );
    }
}

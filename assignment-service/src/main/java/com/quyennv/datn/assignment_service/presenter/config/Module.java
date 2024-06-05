package com.quyennv.datn.assignment_service.presenter.config;

import com.quyennv.datn.assignment_service.adapter.services.user_services.UserService;
import com.quyennv.datn.assignment_service.core.repositories.*;
import com.quyennv.datn.assignment_service.core.usecases.answer_feedback.*;
import com.quyennv.datn.assignment_service.core.usecases.assignment.*;
import com.quyennv.datn.assignment_service.core.usecases.assignment_attempt.AttemptAssignmentUseCase;
import com.quyennv.datn.assignment_service.core.usecases.assignment_attempt.GetAssignmentAttemptByIdUseCase;
import com.quyennv.datn.assignment_service.core.usecases.assignment_attempt.SubmitQuestionAnswerUseCase;
import com.quyennv.datn.assignment_service.core.usecases.question.AddQuestionsToAssignmentUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Module {
    @Bean
    CreateAssignmentUseCase createAssignmentUseCase(AssignmentRepository assignmentRepository) {
        return new CreateAssignmentUseCase(assignmentRepository);
    }

    @Bean
    UpdateAssignmentDetailUseCase updateAssignmentDetailUseCase(AssignmentRepository assignmentRepository) {
        return new UpdateAssignmentDetailUseCase(assignmentRepository);
    }

    @Bean
    AddQuestionsToAssignmentUseCase addQuestionsToAssignmentUseCase(AssignmentRepository assignmentRepository,
                                                                    QuestionRepository questionRepository) {
        return new AddQuestionsToAssignmentUseCase(assignmentRepository, questionRepository);
    }

    @Bean
    GetOneAssignmentUseCase getOneAssignmentUseCase(AssignmentRepository assignmentRepository) {
        return new GetOneAssignmentUseCase(assignmentRepository);
    }
    @Bean
    AttemptAssignmentUseCase attemptAssignmentUseCase(
            AssignmentRepository assignmentRepository,
            AssignmentAttemptRepository assignmentAttemptRepository) {
        return new AttemptAssignmentUseCase(assignmentRepository, assignmentAttemptRepository);
    }

    @Bean
    SubmitQuestionAnswerUseCase submitQuestionAnswerUseCase(
            AssignmentAttemptRepository assignmentAttemptRepository,
            QuestionAnswerRepository questionAnswerRepository,
            UpdateAssignmentScoreUseCase updateAssignmentScoreUseCase) {
        return new SubmitQuestionAnswerUseCase(assignmentAttemptRepository, questionAnswerRepository, updateAssignmentScoreUseCase);
    }

    @Bean
    UpdateAssignmentScoreUseCase updateAssignmentScoreUseCase(AssignmentAttemptRepository assignmentAttemptRepository,
                                                              QuestionAnswerRepository questionAnswerRepository) {
        return new UpdateAssignmentScoreUseCase(assignmentAttemptRepository, questionAnswerRepository);
    }

    @Bean
    SubmitAssignmentUseCase submitAssignmentUseCase(AssignmentAttemptRepository assignmentAttemptRepository, UpdateAssignmentScoreUseCase updateAssignmentScoreUseCase) {
        return new SubmitAssignmentUseCase(assignmentAttemptRepository, updateAssignmentScoreUseCase);
    }

    @Bean
    GetAssignmentInCourseUseCase getAssigmentsUseCase(AssignmentRepository assignmentRepository) {
        return new GetAssignmentInCourseUseCase(assignmentRepository);
    }
    @Bean
    GetAssignmentAttemptByIdUseCase getAssignmentAttemptByIdUseCase(AssignmentAttemptRepository assignmentAttemptRepository) {
        return new GetAssignmentAttemptByIdUseCase(assignmentAttemptRepository);
    }
    @Bean
    TeacherScoreAnswerUseCase teacherScoreAnswerUseCase(QuestionAnswerRepository repo, UpdateAssignmentScoreUseCase updateAssignmentScoreUseCase) {
        return new TeacherScoreAnswerUseCase(repo, updateAssignmentScoreUseCase);
    }
    @Bean
    TeacherAddQuestionFeedBackUseCase teacherAddQuestionFeedBackUseCase(QuestionAnswerFeedbackRepository questionAnswerFeedbackRepository,
                                                                        QuestionAnswerRepository questionAnswerRepository) {
        return new TeacherAddQuestionFeedBackUseCase(questionAnswerFeedbackRepository, questionAnswerRepository);
    }

    @Bean
    UpdateQuestionAnswerFeedbackDataUseCase updateQuestionAnswerFeedbackUseCase(QuestionAnswerFeedbackRepository questionAnswerFeedbackRepository) {
        return new UpdateQuestionAnswerFeedbackDataUseCase(questionAnswerFeedbackRepository);
    }

    @Bean
    DeleteQuestionAnswerFeedbackUseCase deleteQuestionAnswerFeedbackUseCase(QuestionAnswerFeedbackRepository questionAnswerFeedbackRepository) {
        return new DeleteQuestionAnswerFeedbackUseCase(questionAnswerFeedbackRepository);
    }
    @Bean
    TeacherFixLongAnswerUseCase teacherFixLongAnswerUseCase(QuestionAnswerRepository questionAnswerRepository) {
        return new TeacherFixLongAnswerUseCase(questionAnswerRepository);
    }
}

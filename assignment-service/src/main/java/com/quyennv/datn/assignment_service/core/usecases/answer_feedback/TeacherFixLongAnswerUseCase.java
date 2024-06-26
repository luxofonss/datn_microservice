package com.quyennv.datn.assignment_service.core.usecases.answer_feedback;

import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.entities.QuestionAnswer;
import com.quyennv.datn.assignment_service.core.repositories.QuestionAnswerRepository;
import com.quyennv.datn.assignment_service.core.usecases.UseCase;
import lombok.Builder;
import lombok.Value;

public class TeacherFixLongAnswerUseCase extends UseCase<TeacherFixLongAnswerUseCase.InputValues,
        TeacherFixLongAnswerUseCase.OutputValues> {
    private final QuestionAnswerRepository questionAnswerRepository;

    public TeacherFixLongAnswerUseCase(QuestionAnswerRepository questionAnswerRepository) {
        this.questionAnswerRepository = questionAnswerRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        QuestionAnswer questionAnswer = questionAnswerRepository.findById(input.getQuestionAnswerId().getId())
                .orElseThrow(() -> new IllegalArgumentException("Question answer not found"));

        questionAnswer.setTeacherFixedTextAnswer(input.getContent());
        return new OutputValues(questionAnswerRepository.persist(questionAnswer));
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        Identity questionAnswerId;
        String content;
        Identity requesterId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        QuestionAnswer answer;
    }
}

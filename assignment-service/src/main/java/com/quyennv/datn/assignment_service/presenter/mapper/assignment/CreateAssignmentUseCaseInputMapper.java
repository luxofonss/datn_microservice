package com.quyennv.lms.presenter.rest.mapper.assignment;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.enums.*;
import com.quyennv.lms.core.usecases.assignment.CreateAssignmentUseCase;
import com.quyennv.lms.presenter.rest.dto.assignment.CreateAssignmentRequest;
import com.quyennv.lms.presenter.rest.dto.assignment.AssignmentQuestionsMutationRequest;
import com.quyennv.lms.presenter.usecases.security.UserPrincipal;
import com.quyennv.lms.presenter.utils.DateHelper;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Builder
public class CreateAssignmentUseCaseInputMapper {
    public static CreateAssignmentUseCase.InputValues map(UserPrincipal requester, CreateAssignmentRequest req) {
        return CreateAssignmentUseCase.InputValues
                .builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .teacherId(Identity.from(requester.getId()))
                .subjectId(req.getSubjectId())
                .questions(mapQuestions(req.getQuestions()))
                .duration(req.getDuration())
                .startTime(Objects.nonNull(req.getStartTime()) ? DateHelper.toLocalDateTime(req.getStartTime()) : null)
                .endTime(Objects.nonNull(req.getEndTime()) ? DateHelper.toLocalDateTime(req.getEndTime()) : null)
                .assignmentType(Objects.nonNull(req.getAssignmentType()) ? AssignmentType.valueOf(req.getAssignmentType()) : AssignmentType.REGULAR)
                .maxAttemptTimes(req.getMaxAttemptTimes())
                .lessonId(Objects.nonNull(req.getLessonId()) ? Identity.fromString(req.getLessonId()) : null)
                .build();
    }

    private static List<CreateAssignmentUseCase.QuestionInput> mapQuestions(List<AssignmentQuestionsMutationRequest> questions) {
        if (Objects.isNull(questions)) {
            return new ArrayList<>();
        }
        log.info("questions: {}", questions.get(0).getSubQuestions());
        return questions.stream().map(
                q -> CreateAssignmentUseCase.QuestionInput
                        .builder()
                        .level(QuestionLevel.valueOf(q.getLevel()))
                        .title(q.getTitle())
                        .image(q.getImage())
                        .audio(q.getAudio())
                        .mark(q.getMark())
                        .type(QuestionType.valueOf(q.getType()))
                        .answerExplanation(q.getAnswerExplanation())
                        .choices(Objects.isNull(q.getChoices()) ? null : q.getChoices().stream().map(
                                c -> CreateAssignmentUseCase.QuestionChoiceInput
                                        .builder()
                                        .content(c.getContent())
                                        .order(c.getOrder())
                                        .isCorrect(c.getIsCorrect())
                                        .explanation(c.getExplanation())
                                        .build()
                        ).toList())
                        .textAnswers(Objects.isNull(q.getTextAnswers()) ? null : q.getTextAnswers().stream().map(
                                ta -> CreateAssignmentUseCase.QuestionTextAnswerInput
                                        .builder()
                                        .answer(ta.getAnswer())
                                        .explanation(ta.getExplanation())
                                        .build()
                        ).toList())
                        .subQuestions(Objects.nonNull(q.getSubQuestions()) ? mapQuestions(q.getSubQuestions()) : null)
                        .build()
        ).toList();
    }
}

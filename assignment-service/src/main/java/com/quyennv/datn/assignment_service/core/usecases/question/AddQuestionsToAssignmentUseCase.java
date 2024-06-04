package com.quyennv.lms.core.usecases.assignment;

import com.quyennv.lms.core.domain.entities.Assignment;
import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.Question;
import com.quyennv.lms.core.usecases.question.QuestionRepository;

import java.util.List;
import java.util.Objects;

public class AddQuestionsToAssignmentUseCase extends UpdateAssignmentUseCase{
    private final QuestionRepository questionRepository;
    public AddQuestionsToAssignmentUseCase(AssignmentRepository assignmentRepository, QuestionRepository questionRepository) {
        super(assignmentRepository);
        this.questionRepository = questionRepository;
    }

    @Override
    public Assignment update(InputValues input, Assignment assignment) {
        if (Objects.nonNull(input.getQuestions()) && !input.getQuestions().isEmpty()) {
            List<Question> questions = mapQuestions(input.getQuestions(), input);
            List<Identity> ids = questions.stream().map(Question::getId).filter(Objects::nonNull).toList();

            List<Question> existingQuestions = questionRepository.getByIds(ids);
            List<Question> newQuestions = questions.stream()
                    .filter(q -> !existingQuestions.contains(q))
                    .toList();

            return assignment.addQuestions(newQuestions);
        } else {
            throw new RuntimeException("Questions are required");
        }
    }
}

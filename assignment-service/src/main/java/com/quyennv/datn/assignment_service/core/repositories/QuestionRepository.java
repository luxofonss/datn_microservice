package com.quyennv.lms.core.usecases.question;

import com.quyennv.lms.core.domain.entities.Identity;
import com.quyennv.lms.core.domain.entities.Question;

import java.util.List;

public interface QuestionRepository {
    public List<Question> getByIds(List<Identity> ids);
}

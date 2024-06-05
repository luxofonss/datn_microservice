package com.quyennv.datn.assignment_service.core.repositories;

import com.quyennv.datn.assignment_service.core.domain.entities.Identity;
import com.quyennv.datn.assignment_service.core.domain.entities.Question;

import java.util.List;

public interface QuestionRepository {
    public List<Question> getByIds(List<Identity> ids);
}

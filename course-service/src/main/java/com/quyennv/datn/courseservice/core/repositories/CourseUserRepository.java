package com.quyennv.datn.courseservice.core.repositories;


import com.quyennv.datn.courseservice.core.domain.entities.User;

import java.util.List;

public interface CourseUserRepository {
    List<User> getUsersByEmails(List<String> emails);
}

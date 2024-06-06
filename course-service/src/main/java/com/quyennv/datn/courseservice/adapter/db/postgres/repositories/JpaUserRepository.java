package com.quyennv.datn.courseservice.adapter.db.postgres.repositories;

import com.quyennv.datn.courseservice.adapter.db.postgres.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserData, UUID> {
}

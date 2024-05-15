package com.quyennv.datn.courseservice.presenter.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.quyennv.datn.courseservice.adapter.db.postgres.entities"})
@EnableJpaRepositories(basePackages = {"com.quyennv.datn.courseservice.adapter.db.postgres.repositories"})
public class DbConfig {
}

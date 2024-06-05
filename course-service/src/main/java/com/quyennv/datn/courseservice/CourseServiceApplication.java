package com.quyennv.datn.courseservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;

@SpringBootApplication(scanBasePackages = {
		"com.quyennv.datn.courseservice.presenter",
		"com.quyennv.datn.courseservice.adapter"})
@EnableFeignClients
public class CourseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseServiceApplication.class, args);
	}

	@Bean
	NewTopic notification() {
	return new NewTopic("notification", 2, (short) 1);
	}

	@Bean
	JsonMessageConverter converter() {
		return new JsonMessageConverter();
	}
}

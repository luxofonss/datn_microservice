package com.quyennv.datn.courseservice.presenter.config;

import com.quyennv.datn.courseservice.adapter.event_consumer.kafka.cdc.user.UserRepository;
import com.quyennv.datn.courseservice.adapter.services.user_services.UserService;
import com.quyennv.datn.courseservice.core.repositories.*;
import com.quyennv.datn.courseservice.core.usecases.coure.*;
import com.quyennv.datn.courseservice.core.usecases.course_student.*;
import com.quyennv.datn.courseservice.core.usecases.lesson.*;
import com.quyennv.datn.courseservice.core.usecases.section.CreateSectionsUseCase;
import com.quyennv.datn.courseservice.core.usecases.section.DeleteSectionsUseCase;
import com.quyennv.datn.courseservice.core.usecases.section.UpdateSectionsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Module {
    @Bean
    CreateCourseUseCase createCourseUseCase(CourseRepository repository) {
        return new CreateCourseUseCase(repository);
    }

    @Bean
    CreateSectionsUseCase createSectionUseCase(CourseRepository repository) {
        return new CreateSectionsUseCase(repository);
    }

    @Bean
    UpdateSectionsUseCase updateSectionsUseCase(CourseRepository repository) {
        return new UpdateSectionsUseCase(repository);
    }

    @Bean
    DeleteSectionsUseCase deleteSectionsUseCase(CourseRepository repository){
        return new DeleteSectionsUseCase(repository);
    }

    @Bean
    CreateLessonsUseCase createLessonsUseCase(CourseRepository repository) {
        return new CreateLessonsUseCase(repository);
    }

    @Bean
    UpdateLessonsUseCase updateLessonsUseCase(CourseRepository repository) {
        return new UpdateLessonsUseCase(repository);
    }

    @Bean
    DeleteLessonsUseCase deleteLessonsUseCase(CourseRepository repository){
        return new DeleteLessonsUseCase(repository);
    }

    @Bean
    GetOneCourseUseCase getOneCourseUseCase(CourseRepository repository){
        return new GetOneCourseUseCase(repository);
    }

    @Bean
    GetCreatedCoursesUseCase getCreatedCoursesUseCase(CourseRepository repository) {
        return new GetCreatedCoursesUseCase(repository);
    }

    @Bean
    GetAllCourseUseCase getAllCourseUseCase(CourseRepository repository) {
        return new GetAllCourseUseCase(repository);
    }

    @Bean
    UpdateCourseDetailUseCase updateCourseDetailUseCase(CourseRepository repository) {
        return new UpdateCourseDetailUseCase(repository);
    }

    @Bean
    DeleteCourseUseCase deleteCourseUseCase(CourseRepository repository) {
        return new DeleteCourseUseCase(repository);
    }
    @Bean
    StudentRegisterForCourseUseCase studentRegisterForCourseUseCase(CourseRepository courseRepository,
                                                                    CourseStudentRepository courseStudentRepository) {
        return new StudentRegisterForCourseUseCase(courseRepository, courseStudentRepository);
    }
    @Bean
    TeacherAddStudentsToCourseUseCase teacherAddStudentsToCourseUseCase(CourseRepository courseRepository,
                                                                        CourseStudentRepository courseStudentRepository,
                                                                        UserService userService
    ) {
        return new TeacherAddStudentsToCourseUseCase(courseRepository, courseStudentRepository, userService);
    }

    @Bean
    UpdateCourseStudentStatusUseCase updateCourseStudentUseCase(CourseStudentRepository courseStudentRepository) {
        return new UpdateCourseStudentStatusUseCase(courseStudentRepository);
    }

    @Bean
    DeleteCourseStudentUseCase deleteCourseStudentUseCase(CourseStudentRepository courseStudentRepository) {
        return new DeleteCourseStudentUseCase(courseStudentRepository);
    }

    @Bean
    CourseStudentsGetAllUseCase courseStudentsGetAllUseCase(CourseRepository courseRepository, CourseStudentRepository courseStudentRepository) {
        return new CourseStudentsGetAllUseCase(courseRepository, courseStudentRepository);
    }

    @Bean
    StudentGetRegisteredCoursesUseCase studentGetRegisteredCoursesUseCase(CourseStudentRepository courseStudentRepository) {
        return new StudentGetRegisteredCoursesUseCase(courseStudentRepository);
    }

    @Bean
    StudentJoinCourseByCodeUseCase studentJoinCourseByCodeUseCase(CourseRepository courseRepository,
                                                                  CourseStudentRepository courseStudentRepository,
                                                                  UserRepository userRepository) {
        return new StudentJoinCourseByCodeUseCase(courseRepository, courseStudentRepository, userRepository);
    }
    @Bean
    CreateLessonStudyStatusUseCase createLessonStudyStatusUseCase(LessonStudentRepository lessonStudentRepository) {
        return new CreateLessonStudyStatusUseCase(lessonStudentRepository);
    }

    @Bean
    UpdateLessonStudyStatusUseCase updateLessonStudyStatusUseCase(LessonStudentRepository lessonStudentRepository) {
        return new UpdateLessonStudyStatusUseCase(lessonStudentRepository);
    }

    @Bean
    UpdateLessonAssigmentUseCase updateLessonAssigmentUseCase(LessonRepository lessonRepository) {
        return new UpdateLessonAssigmentUseCase(lessonRepository);
    }
}

package com.quyennv.datn.assignment_service.presenter.rest.course;

import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.core.domain.entities.Identity;
import com.quyennv.datn.courseservice.core.domain.enums.EnrollStatus;
import com.quyennv.datn.courseservice.core.usecases.UseCaseExecutor;
import com.quyennv.datn.courseservice.core.usecases.coure.*;
import com.quyennv.datn.courseservice.core.usecases.course_student.*;
import com.quyennv.datn.courseservice.core.usecases.lesson.*;
import com.quyennv.datn.courseservice.core.usecases.section.CreateSectionsUseCase;
import com.quyennv.datn.courseservice.core.usecases.section.DeleteSectionsUseCase;
import com.quyennv.datn.courseservice.core.usecases.section.UpdateSectionsUseCase;
import com.quyennv.datn.courseservice.presenter.dto.ApiResponse;
import com.quyennv.datn.courseservice.presenter.dto.course.*;
import com.quyennv.datn.courseservice.presenter.mapper.course.*;
import com.quyennv.datn.courseservice.presenter.mapper.lesson.LessonMutationInputMapper;
import com.quyennv.datn.courseservice.presenter.mapper.section.SectionMutationInputMapper;
import com.quyennv.datn.courseservice.presenter.usecases.security.CurrentUser;
import com.quyennv.datn.courseservice.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Controller
@Slf4j
public class CourseController implements CourseResource{
    private final UseCaseExecutor useCaseExecutor;
    private final CreateCourseUseCase createCourseUseCase;
    private final CreateSectionsUseCase createSectionsUseCase;
    private final UpdateSectionsUseCase updateSectionsUseCase;
    private final DeleteSectionsUseCase deleteSectionsUseCase;
    private final GetOneCourseUseCase getOneCourseUseCase;
    private final CreateLessonsUseCase createLessonsUseCase;
    private final UpdateLessonsUseCase updateLessonsUseCase;
    private final DeleteLessonsUseCase deleteLessonsUseCase;
    private final GetCreatedCoursesUseCase getCreatedCoursesUseCase;
    private final GetAllCourseUseCase getAllCourseUseCase;
    private final UpdateCourseDetailUseCase updateCourseDetailUseCase;
    private final DeleteCourseUseCase deleteCourseUseCase;
    private final StudentRegisterForCourseUseCase studentRegisterForCourseUseCase;
    private final TeacherAddStudentsToCourseUseCase teacherAddStudentsToCourseUseCase;
    private final UpdateCourseStudentStatusUseCase updateCourseStudentStatusUseCase;
    private final DeleteCourseStudentUseCase deleteCourseStudentUseCase;
    private final CourseStudentsGetAllUseCase courseStudentsGetAllUseCase;
    private final StudentGetRegisteredCoursesUseCase studentGetRegisteredCoursesUseCase;
    private final StudentJoinCourseByCodeUseCase studentJoinCourseByCodeUseCase;
    private final CreateLessonStudyStatusUseCase createLessonStudyStatusUseCase;
    private final UpdateLessonStudyStatusUseCase updateLessonStudyStatusUseCase;

    public CourseController(UseCaseExecutor useCaseExecutor,
                            CreateCourseUseCase createCourseUseCase,
                            CreateSectionsUseCase createSectionsUseCase,
                            UpdateSectionsUseCase updateSectionsUseCase,
                            DeleteSectionsUseCase deleteSectionsUseCase,
                            GetOneCourseUseCase getOneCourseUseCase,
                            CreateLessonsUseCase createLessonsUseCase,
                            UpdateLessonsUseCase updateLessonsUseCase,
                            DeleteLessonsUseCase deleteLessonsUseCase,
                            GetCreatedCoursesUseCase getCreatedCoursesUseCase,
                            GetAllCourseUseCase getAllCourseUseCase,
                            UpdateCourseDetailUseCase updateCourseDetailUseCase,
                            DeleteCourseUseCase deleteCourseUseCase,
                            StudentRegisterForCourseUseCase studentRegisterForCourseUseCase,
                            TeacherAddStudentsToCourseUseCase teacherAddStudentsToCourseUseCase,
                            UpdateCourseStudentStatusUseCase updateCourseStudentStatusUseCase,
                            DeleteCourseStudentUseCase deleteCourseStudentUseCase,
                            CourseStudentsGetAllUseCase courseStudentsGetAllUseCase,
                            StudentGetRegisteredCoursesUseCase studentGetRegisteredCoursesUseCase,
                            StudentJoinCourseByCodeUseCase studentJoinCourseByCodeUseCase,
                            CreateLessonStudyStatusUseCase createLessonStudyStatusUseCase,
                            UpdateLessonStudyStatusUseCase updateLessonStudyStatusUseCase) {
        this.useCaseExecutor = useCaseExecutor;
        this.createCourseUseCase = createCourseUseCase;
        this.createSectionsUseCase = createSectionsUseCase;
        this.updateSectionsUseCase = updateSectionsUseCase;
        this.deleteSectionsUseCase = deleteSectionsUseCase;
        this.getOneCourseUseCase = getOneCourseUseCase;
        this.createLessonsUseCase = createLessonsUseCase;
        this.updateLessonsUseCase = updateLessonsUseCase;
        this.deleteLessonsUseCase = deleteLessonsUseCase;
        this.getCreatedCoursesUseCase = getCreatedCoursesUseCase;
        this.getAllCourseUseCase = getAllCourseUseCase;
        this.updateCourseDetailUseCase = updateCourseDetailUseCase;
        this.deleteCourseUseCase = deleteCourseUseCase;
        this.studentRegisterForCourseUseCase = studentRegisterForCourseUseCase;
        this.teacherAddStudentsToCourseUseCase = teacherAddStudentsToCourseUseCase;
        this.updateCourseStudentStatusUseCase = updateCourseStudentStatusUseCase;
        this.deleteCourseStudentUseCase = deleteCourseStudentUseCase;
        this.courseStudentsGetAllUseCase = courseStudentsGetAllUseCase;
        this.studentGetRegisteredCoursesUseCase = studentGetRegisteredCoursesUseCase;
        this.studentJoinCourseByCodeUseCase = studentJoinCourseByCodeUseCase;
        this.createLessonStudyStatusUseCase = createLessonStudyStatusUseCase;
        this.updateLessonStudyStatusUseCase = updateLessonStudyStatusUseCase;
    }

    @Override
    public CompletableFuture<ApiResponse<UUID>> create(
            CreateCourseRequest req,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                createCourseUseCase,
                CreateCourseUseCaseInputMapper.map(requester, req),
                outputValues -> new ApiResponse<>(true, "ok", outputValues.getCourse().getId().getUUID())
        );
    }

    @Override
    public ResponseEntity<ApiResponse<String>> ping(
            @CurrentUser UserPrincipal requester
    ) {
        return ResponseEntity.ok(new ApiResponse<>(true, "ok", "pong"));
    }

    @Override
    public CompletableFuture<ApiResponse<List<Course>>> getAllCourses(
            GetCoursesRequest request,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {

        return useCaseExecutor.execute(
                getAllCourseUseCase,
                GetCourseUseCaseInputMapper.map(request, requester),
                outputValues -> new ApiResponse<>(true, "ok", outputValues.getCourses())
        );
    }


    @Override
    public CompletableFuture<ApiResponse<UUID>> updateCourse(
            UpdateCourseRequest req,
            String id,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                updateCourseDetailUseCase,
                CourseMutationDetailInputMapper.map(requester, req, id),
                outputValues -> new ApiResponse<>(true, "ok", outputValues.getCourse().getId().getUUID())

        );
    }

    @Override
    public CompletableFuture<ApiResponse<UUID>> deleteCourse(
            String id,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                deleteCourseUseCase,
                CourseMutationDetailInputMapper.map(requester, id),
                outputValues -> new ApiResponse<>(true, "ok", outputValues.getCourse().getId().getUUID())
        );
    }

    @Override
    public CompletableFuture<ApiResponse<List<Course>>> getAllMyCourses(
            GetCoursesRequest request,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                getCreatedCoursesUseCase,
                GetCourseUseCaseInputMapper.map(request, requester),
                outputValues -> new ApiResponse<>(true, "ok", outputValues.getCourses())
        );
    }

    @Override
    public CompletableFuture<ApiResponse<?>> getAllMyRegisteredCourses(
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                studentGetRegisteredCoursesUseCase,
                StudentGetRegisteredCoursesUseCase.InputValues.builder().userId(Identity.from(requester.getId())).build(),
                outputValues -> new ApiResponse<>(true, "ok", outputValues.getCourses())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> getById(String id, UserPrincipal requester, HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                getOneCourseUseCase,
                new GetOneCourseUseCase.InputValues(Identity.fromString(id), Identity.from(requester.getId())),
                outputValues -> new ApiResponse(true,"ok", outputValues.getCourse())
        );
    }


    @Override
    public CompletableFuture<ApiResponse<?>> addSections(
            UpdateCourseRequest req,
            String id,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                createSectionsUseCase,
                SectionMutationInputMapper.map(requester, req, id),
                outputValues -> new ApiResponse<>(true, "ok", outputValues.getCourse().getId())
        );
    }
//course: b457825d-bcfb-465a-9233-ab38833d7aae
//    section: 4d2a4efc-79b4-4ce7-9d3c-0c4fd744252d
//        lesson:5c6347c9-3980-4e45-8bed-7cb6707ad716 lesson1
    @Override
    public CompletableFuture<ApiResponse> updateSections(
            UpdateCourseRequest req,
            String id,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                updateSectionsUseCase,
                SectionMutationInputMapper.map(requester, req, id),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourse().getId())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> deleteSections(
            UpdateCourseRequest req,
            String id,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                deleteSectionsUseCase,
                SectionMutationInputMapper.map(requester, req, id),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourse().getId())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> addLessons(
            UpdateCourseRequest req,
            String courseId,
            String sectionId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                createLessonsUseCase,
                LessonMutationInputMapper.map(requester, req, courseId, sectionId),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourse().getId())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> addStudyStatus(
            CreateStudyStatusRequest req,
            String courseId,
            String sectionId,
            String lessonId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                createLessonStudyStatusUseCase,
                CreateLessonStudyStatusUseCase.InputValues
                        .builder()
                        .lessonId(lessonId)
                        .studentId(requester.getId().toString())
                        .status(req.getStatus())
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getData())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> updateStudyStatus(
            CreateStudyStatusRequest req,
            String courseId,
            String sectionId,
            String lessonId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                updateLessonStudyStatusUseCase,
                UpdateLessonStudyStatusUseCase.InputValues
                        .builder()
                        .lessonId(lessonId)
                        .studentId(requester.getId().toString())
                        .status(req.getStatus())
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getData())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> updateLessons(
            UpdateCourseRequest req,
            String courseId,
            String sectionId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                updateLessonsUseCase,
                LessonMutationInputMapper.map(requester, req, courseId, sectionId),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourse().getId())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> deleteLessons(
            UpdateCourseRequest req,
            String courseId,
            String sectionId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                deleteLessonsUseCase,
                LessonMutationInputMapper.map(requester, req, courseId, sectionId),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourse().getId())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> studentRegisterForCourse(
            String courseId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                studentRegisterForCourseUseCase,
                RegisterForCourseUseCase.InputValues
                        .builder()
                        .courseId(Identity.fromString(courseId))
                        .emails(List.of(requester.getEmail()))
                        .requesterId(Identity.from(requester.getId()))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourseStudents().get(0))
        );
    }

    @Override
    public CompletableFuture<ApiResponse> studentRegisterWithCode(
            StudentRegisterWithCodeRequest req,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                studentJoinCourseByCodeUseCase,
                StudentJoinCourseByCodeUseCase.InputValues
                        .builder()
                        .code(req.getCode())
                        .requester(Identity.from(requester.getId()))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourseStudent())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> getAllStudentsInCourse(
            String courseId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                courseStudentsGetAllUseCase,
                CourseStudentsGetAllUseCase.InputValues
                        .builder()
                        .courseId(Identity.fromString(courseId))
                        .requesterId(Identity.from(requester.getId()))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getStudents())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> updateStudentInCourse(
            String courseId,
            String studentId,
            UpdateCourseStudentStatusRequest req,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        log.info("updateStudentInCourse: courseId: {}, studentId: {}, status: {}", courseId, studentId, req.getStatus());
        return useCaseExecutor.execute(
                updateCourseStudentStatusUseCase,
                UpdateCourseStudentUseCase.InputValues
                        .builder()
                        .courseId(Identity.fromString(courseId))
                        .userId(Identity.fromString(studentId))
                        .status(EnrollStatus.valueOf(req.getStatus()))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourseStudent())

        );
    }

    @Override
    public CompletableFuture<ApiResponse> deleteStudentInCourse(
            String courseId,
            String studentId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                deleteCourseStudentUseCase,
                UpdateCourseStudentUseCase.InputValues
                        .builder()
                        .courseId(Identity.fromString(courseId))
                        .userId(Identity.fromString(studentId))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourseStudent())
        );
    }

    @Override
    public CompletableFuture<ApiResponse> teacherAddStudentsToCourse(
            AddStudentToCourseRequest req,
            String courseId,
            UserPrincipal requester,
            HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                teacherAddStudentsToCourseUseCase,
                RegisterForCourseUseCase.InputValues
                        .builder()
                        .courseId(Identity.fromString(courseId))
                        .emails(req.getEmails())
                        .requestToken(httpServletRequest.getHeader("Authorization"))
                        .build(),
                outputValues -> new ApiResponse(true, "ok", outputValues.getCourseStudents())
        );
    }
}

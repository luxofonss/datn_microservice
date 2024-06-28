package com.quyennv.datn.courseservice.presenter.rest.course;

import com.quyennv.datn.courseservice.core.domain.entities.Course;
import com.quyennv.datn.courseservice.presenter.dto.ApiResponse;
import com.quyennv.datn.courseservice.presenter.dto.course.*;
import com.quyennv.datn.courseservice.presenter.usecases.security.CurrentUser;
import com.quyennv.datn.courseservice.presenter.usecases.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping
public interface CourseResource {
    @PostMapping
    CompletableFuture<ApiResponse<UUID>> create(
            @Valid @RequestBody CreateCourseRequest req,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
            );

    @GetMapping("/ping")
    ResponseEntity<ApiResponse<String>> ping(
            @CurrentUser UserPrincipal principal
    );

    @GetMapping
    CompletableFuture<ApiResponse<List<Course>>> getAllCourses(
            @Valid @ModelAttribute GetCoursesRequest request,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PutMapping("/{id}")
    CompletableFuture<ApiResponse<UUID>> updateCourse(
            @Valid @RequestBody UpdateCourseRequest req,
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @DeleteMapping("/{id}")
    CompletableFuture<ApiResponse<UUID>> deleteCourse(
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @GetMapping("/my-courses")
    CompletableFuture<ApiResponse<List<Course>>> getAllMyCourses(
            @Valid @ModelAttribute GetCoursesRequest request,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @GetMapping("/my-registered-courses")
    CompletableFuture<ApiResponse<?>> getAllMyRegisteredCourses(
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @GetMapping("/{id}")
    CompletableFuture<ApiResponse> getById(
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @GetMapping("/internal/{id}")
    CompletableFuture<ApiResponse> getCourseByIdInternal(
            @PathVariable String id,
            HttpServletRequest httpServletRequest
    );


    @PostMapping("/{id}/sections")
    CompletableFuture<ApiResponse<?>> addSections(
            @Valid @RequestBody UpdateCourseRequest req,
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
            );

    @PutMapping("/{id}/sections")
    CompletableFuture<ApiResponse> updateSections(
            @Valid @RequestBody UpdateCourseRequest req,
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @DeleteMapping("/{id}/sections")
    CompletableFuture<ApiResponse> deleteSections(
            @Valid @RequestBody UpdateCourseRequest req,
            @PathVariable String id,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PostMapping("/{courseId}/sections/{sectionId}/lessons")
    CompletableFuture<ApiResponse> addLessons(
            @Valid @RequestBody UpdateCourseRequest req,
            @PathVariable String courseId,
            @PathVariable String sectionId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PostMapping("/{courseId}/sections/{sectionId}/lessons/{lessonId}/study-status")
    CompletableFuture<ApiResponse> addStudyStatus(
            @Valid @RequestBody CreateStudyStatusRequest req,
            @PathVariable String courseId,
            @PathVariable String sectionId,
            @PathVariable String lessonId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PutMapping("/{courseId}/sections/{sectionId}/lessons/{lessonId}/study-status")
    CompletableFuture<ApiResponse> updateStudyStatus(
            @Valid @RequestBody CreateStudyStatusRequest req,
            @PathVariable String courseId,
            @PathVariable String sectionId,
            @PathVariable String lessonId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PutMapping("/{courseId}/sections/{sectionId}/lessons")
    CompletableFuture<ApiResponse> updateLessons(
            @Valid @RequestBody UpdateCourseRequest req,
            @PathVariable String courseId,
            @PathVariable String sectionId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @DeleteMapping("/{courseId}/sections/{sectionId}/lessons")
    CompletableFuture<ApiResponse> deleteLessons(
            @Valid @RequestBody UpdateCourseRequest req,
            @PathVariable String courseId,
            @PathVariable String sectionId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PostMapping("/{courseId}/students")
    CompletableFuture<ApiResponse> studentRegisterForCourse(
            @PathVariable String courseId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PostMapping("/register-with-code")
    CompletableFuture<ApiResponse> studentRegisterWithCode(
            @Valid @RequestBody StudentRegisterWithCodeRequest req,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @GetMapping("/{courseId}/students")
    CompletableFuture<ApiResponse> getAllStudentsInCourse(
            @PathVariable String courseId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PutMapping("/{courseId}/students/{studentId}")
    CompletableFuture<ApiResponse> updateStudentInCourse(
            @PathVariable String courseId,
            @PathVariable String studentId,
            @Valid @RequestBody UpdateCourseStudentStatusRequest req,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @DeleteMapping("/{courseId}/students/{studentId}")
    CompletableFuture<ApiResponse> deleteStudentInCourse(
            @PathVariable String courseId,
            @PathVariable String studentId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

    @PostMapping("/{courseId}/students/add-many")
    CompletableFuture<ApiResponse> teacherAddStudentsToCourse(
            @Valid @RequestBody AddStudentToCourseRequest req,
            @PathVariable String courseId,
            @CurrentUser UserPrincipal requester,
            HttpServletRequest httpServletRequest
    );

}

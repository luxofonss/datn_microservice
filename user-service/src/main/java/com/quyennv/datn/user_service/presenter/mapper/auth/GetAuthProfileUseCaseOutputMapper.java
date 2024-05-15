package com.quyennv.datn.user_service.presenter.mapper.auth;

import com.quyennv.datn.user_service.core.domain.entities.User;
import com.quyennv.datn.user_service.presenter.dto.ApiResponse;
import com.quyennv.datn.user_service.presenter.dto.user.UserProfileResponse;
import org.springframework.http.ResponseEntity;

public class GetAuthProfileUseCaseOutputMapper {
    public static ResponseEntity<ApiResponse> map(
            User user
    ) {
        return ResponseEntity.ok(new ApiResponse(true, "Get user successfully!",
                new UserProfileResponse(
                    user.getId().getId().toString(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getAvatar(),
                    user.getPhoneNumber(),
                    user.getGender(),
                    user.getRole(),
                    user.getDateOfBirth().toString(),
                    user.getIsVerified(),
                    user.getAddress(),
                    user.getLearnerInfo(),
                    user.getTeacherInfo(),
                    user.getCreatedAt()
                )
        ));
    }
}

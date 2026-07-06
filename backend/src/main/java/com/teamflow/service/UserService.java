package com.teamflow.service;

import com.teamflow.dto.UpdateProfileRequest;
import com.teamflow.dto.UserResponse;

public interface UserService {

    UserResponse getProfile(Long userId);

    UserResponse updateProfile(Long userId, UpdateProfileRequest request);

}
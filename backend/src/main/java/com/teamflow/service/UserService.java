package com.teamflow.service;

import java.util.List;

import com.teamflow.dto.UpdateProfileRequest;
import com.teamflow.dto.UserResponse;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getProfile(Long userId);

    UserResponse updateProfile(Long userId, UpdateProfileRequest request);

}
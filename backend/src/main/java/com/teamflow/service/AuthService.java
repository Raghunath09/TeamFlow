package com.teamflow.service;

import com.teamflow.dto.LoginRequest;
import com.teamflow.dto.RegisterRequest;
import com.teamflow.dto.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);

    String login(LoginRequest request);
}
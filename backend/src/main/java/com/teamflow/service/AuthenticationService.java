package com.teamflow.service;

import com.teamflow.dto.LoginRequest;
import com.teamflow.dto.LoginResponse;
import com.teamflow.dto.RegisterRequest;
import com.teamflow.dto.UserResponse;

public interface AuthenticationService {

    UserResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}
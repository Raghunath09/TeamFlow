package com.teamflow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teamflow.common.ApiResponse;
import com.teamflow.dto.LoginRequest;
import com.teamflow.dto.LoginResponse;
import com.teamflow.dto.RegisterRequest;
import com.teamflow.dto.UserResponse;
import com.teamflow.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(
            @Valid @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "User registered successfully",
                        authenticationService.register(request)
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Login successful",
                        authenticationService.login(request)
                )
        );
    }
}
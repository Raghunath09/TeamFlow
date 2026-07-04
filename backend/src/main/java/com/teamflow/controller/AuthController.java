package com.teamflow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.teamflow.common.ApiResponse;
import com.teamflow.dto.RegisterRequest;
import com.teamflow.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(
            @Validated
            @RequestBody RegisterRequest request){

        return ResponseEntity.ok(

                ApiResponse.builder()

                        .success(true)

                        .message("User Registered Successfully")

                        .data(authService.register(request))

                        .build()

        );

    }

}
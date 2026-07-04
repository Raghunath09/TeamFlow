package com.teamflow.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teamflow.dto.LoginRequest;
import com.teamflow.dto.RegisterRequest;
import com.teamflow.dto.UserResponse;
import com.teamflow.entity.User;
import com.teamflow.repository.UserRepository;
import com.teamflow.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .theme(user.getTheme())
                .build();

    }

    @Override
    public String login(LoginRequest request) {

        return "JWT will be implemented next";

    }

}
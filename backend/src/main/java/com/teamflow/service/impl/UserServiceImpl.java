package com.teamflow.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.teamflow.dto.UpdateProfileRequest;
import com.teamflow.dto.UserResponse;
import com.teamflow.entity.User;
import com.teamflow.repository.UserRepository;
import com.teamflow.security.SecurityUtils;
import com.teamflow.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
        public List<UserResponse> getAllUsers() {

        if (!SecurityUtils.isAdmin()) {
                throw new AccessDeniedException("Only admins can view all users.");
        }

        return userRepository.findAll()
                .stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .fullName(user.getFullName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .theme(user.getTheme())
                        .build())
                .collect(Collectors.toList());
        }

    @Override
    public UserResponse getProfile(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .theme(user.getTheme())
                .build();
    }

    @Override
    public UserResponse updateProfile(Long userId, UpdateProfileRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFullName(request.getFullName());
        user.setTheme(request.getTheme());

        User updatedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(updatedUser.getId())
                .fullName(updatedUser.getFullName())
                .email(updatedUser.getEmail())
                .role(updatedUser.getRole())
                .theme(updatedUser.getTheme())
                .build();
    }
}
package com.teamflow.dto;

import com.teamflow.enums.Theme;
import com.teamflow.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Long id;

    private String fullName;

    private String email;

    private UserRole role;

    private Theme theme;
}
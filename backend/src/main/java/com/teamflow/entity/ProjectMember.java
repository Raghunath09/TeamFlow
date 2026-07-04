package com.teamflow.entity;

import java.time.LocalDateTime;

import com.teamflow.enums.ProjectMemberRole;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many project members belong to one project
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    // Many project members refer to one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private ProjectMemberRole role;

    private LocalDateTime joinedAt;

    @PrePersist
    public void prePersist() {

        joinedAt = LocalDateTime.now();

        if (role == null) {
            role = ProjectMemberRole.MEMBER;
        }
    }
}
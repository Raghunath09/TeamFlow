package com.teamflow.service.impl;

import java.util.List;

import com.teamflow.entity.User;

import org.springframework.stereotype.Service;

import com.teamflow.dto.ActivityLogResponse;
import com.teamflow.dto.CreateActivityLogRequest;
import com.teamflow.entity.ActivityLog;
import com.teamflow.entity.Project;
import com.teamflow.repository.ActivityLogRepository;
import com.teamflow.repository.ProjectRepository;
import com.teamflow.repository.UserRepository;
import com.teamflow.service.ActivityLogService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityLogServiceImpl implements ActivityLogService {

    private final ActivityLogRepository activityLogRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ActivityLogResponse createActivity(CreateActivityLogRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        ActivityLog activity = ActivityLog.builder()
                .action(request.getAction())
                .user(user)
                .project(project)
                .build();

        ActivityLog savedActivity = activityLogRepository.save(activity);

        return ActivityLogResponse.builder()
                .id(savedActivity.getId())
                .action(savedActivity.getAction())
                .userId(user.getId())
                .userName(user.getFullName())
                .projectId(project.getId())
                .projectName(project.getProjectName())
                .createdAt(savedActivity.getCreatedAt())
                .build();
    }

    @Override
    public List<ActivityLogResponse> getProjectActivities(Long projectId) {

        return activityLogRepository.findByProjectId(projectId)
                .stream()
                .map(activity -> ActivityLogResponse.builder()
                        .id(activity.getId())
                        .action(activity.getAction())
                        .userId(activity.getUser().getId())
                        .userName(activity.getUser().getFullName())
                        .projectId(activity.getProject().getId())
                        .projectName(activity.getProject().getProjectName())
                        .createdAt(activity.getCreatedAt())
                        .build())
                .toList();
    }
}
package com.teamflow.service.impl;

import org.springframework.stereotype.Service;

import com.teamflow.dto.DashboardResponse;
import com.teamflow.repository.AttachmentRepository;
import com.teamflow.repository.CommentRepository;
import com.teamflow.repository.NotificationRepository;
import com.teamflow.repository.ProjectMemberRepository;
import com.teamflow.repository.ProjectRepository;
import com.teamflow.repository.TaskRepository;
import com.teamflow.service.DashboardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final CommentRepository commentRepository;
    private final NotificationRepository notificationRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public DashboardResponse getDashboard() {

        long totalProjects = projectRepository.count();

        long totalTasks = taskRepository.count();

        long completedTasks = taskRepository.countByStatus(
                com.teamflow.enums.TaskStatus.DONE);

        long pendingTasks = taskRepository.countByStatus(
                com.teamflow.enums.TaskStatus.TODO);

        long totalMembers = projectMemberRepository.count();

        long totalComments = commentRepository.count();

        long totalNotifications = notificationRepository.count();

        long totalAttachments = attachmentRepository.count();

        return DashboardResponse.builder()
                .totalProjects(totalProjects)
                .totalTasks(totalTasks)
                .completedTasks(completedTasks)
                .pendingTasks(pendingTasks)
                .totalMembers(totalMembers)
                .totalComments(totalComments)
                .totalNotifications(totalNotifications)
                .totalAttachments(totalAttachments)
                .build();
    }

}
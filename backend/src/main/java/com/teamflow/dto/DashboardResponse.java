package com.teamflow.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponse {

    private long totalProjects;

    private long totalTasks;

    private long completedTasks;

    private long pendingTasks;

    private long totalMembers;

    private long totalComments;

    private long totalNotifications;

    private long totalAttachments;

}
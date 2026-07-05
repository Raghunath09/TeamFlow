package com.teamflow.service;

import java.util.List;

import com.teamflow.dto.ActivityLogResponse;
import com.teamflow.dto.CreateActivityLogRequest;

public interface ActivityLogService {

    ActivityLogResponse createActivity(CreateActivityLogRequest request);

    List<ActivityLogResponse> getProjectActivities(Long projectId);

}
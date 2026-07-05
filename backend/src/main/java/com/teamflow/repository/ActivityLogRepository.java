package com.teamflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.teamflow.entity.ActivityLog;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

    @EntityGraph(attributePaths = {"user", "project"})
    List<ActivityLog> findByProjectId(Long projectId);

}
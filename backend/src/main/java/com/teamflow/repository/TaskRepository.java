package com.teamflow.repository;

import java.util.List;

import com.teamflow.enums.TaskStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamflow.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProjectId(Long projectId);

    long countByStatus(TaskStatus status);

}
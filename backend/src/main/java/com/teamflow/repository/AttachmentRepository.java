package com.teamflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.teamflow.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    @EntityGraph(attributePaths = {"task"})
    List<Attachment> findByTaskId(Long taskId);

}
package com.teamflow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.teamflow.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @EntityGraph(attributePaths = {"user"})
    List<Notification> findByUserId(Long userId);

    @Override
    @EntityGraph(attributePaths = {"user"})
    Optional<Notification> findById(Long id);

}
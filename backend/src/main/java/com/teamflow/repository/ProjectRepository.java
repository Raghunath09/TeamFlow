package com.teamflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamflow.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
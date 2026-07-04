package com.teamflow.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "task_relations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depends_on_task_id", nullable = false)
    private Task dependsOnTask;

}
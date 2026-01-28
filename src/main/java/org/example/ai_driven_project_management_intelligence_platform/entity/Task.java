package org.example.ai_driven_project_management_intelligence_platform.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Integer taskId;

    @Column(name = "story_id", nullable = false)
    private Integer storyId;

    @Column(name = "task_title", nullable = false, length = 128)
    private String taskTitle;

    @Column(name = "task_desc", columnDefinition = "text")
    private String taskDesc;

    @Column(name = "assignee_id")
    private Integer assigneeId;

    @Column(name = "status", nullable = false, columnDefinition = "tinyint(1) DEFAULT 0")
    private Byte status = 0;

    @Column(name = "priority", nullable = false, columnDefinition = "tinyint(1) DEFAULT 2")
    private Byte priority = 2;

    @Column(name = "estimated_hours", nullable = false, precision = 5, scale = 1)
    private BigDecimal estimatedHours;

    @Column(name = "remaining_hours", nullable = false, precision = 5, scale = 1)
    private BigDecimal remainingHours;

    @Column(name = "actual_hours", precision = 5, scale = 1)
    private BigDecimal actualHours;

    @Column(name = "sm_approve_id")
    private Integer smApproveId;

    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    @Column(name = "finish_time")
    private LocalDateTime finishTime;

    @PrePersist
    protected void onCreate() {
        if (createTime == null) {
            createTime = LocalDateTime.now();
        }
        if (updateTime == null) {
            updateTime = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = LocalDateTime.now();
    }
}


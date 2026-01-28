package org.example.ai_driven_project_management_intelligence_platform.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sprint")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sprint_id", nullable = false)
    private Integer sprintId;

    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(name = "sprint_name", nullable = false, length = 64)
    private String sprintName;

    @Column(name = "sprint_goal", columnDefinition = "text")
    private String sprintGoal;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "status", nullable = false, columnDefinition = "tinyint(1) DEFAULT 0")
    private Byte status = 0;

    @Column(name = "is_locked", nullable = false, columnDefinition = "tinyint(1) DEFAULT 0")
    private Byte isLocked = 0;

    @Column(name = "creator_id", nullable = false)
    private Integer creatorId;

    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

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


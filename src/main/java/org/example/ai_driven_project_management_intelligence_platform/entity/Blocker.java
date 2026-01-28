package org.example.ai_driven_project_management_intelligence_platform.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "blocker")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blocker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blocker_id", nullable = false)
    private Integer blockerId;

    @Column(name = "task_id", nullable = false)
    private Integer taskId;

    @Column(name = "blocker_desc", nullable = false, columnDefinition = "text")
    private String blockerDesc;

    @Column(name = "urgency_level", nullable = false, columnDefinition = "tinyint(1) DEFAULT 2")
    private Byte urgencyLevel = 2;

    @Column(name = "handler_id")
    private Integer handlerId;

    @Column(name = "status", nullable = false, columnDefinition = "tinyint(1) DEFAULT 0")
    private Byte status = 0;

    @Column(name = "solve_desc", columnDefinition = "text")
    private String solveDesc;

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

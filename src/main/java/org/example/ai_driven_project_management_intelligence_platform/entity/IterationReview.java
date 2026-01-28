package org.example.ai_driven_project_management_intelligence_platform.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "iteration_review")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IterationReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Integer reviewId;

    @Column(name = "sprint_id", nullable = false)
    private Integer sprintId;

    @Column(name = "review_content", nullable = false, columnDefinition = "text")
    private String reviewContent;

    @Column(name = "success_points", columnDefinition = "text")
    private String successPoints;

    @Column(name = "improve_points", columnDefinition = "text")
    private String improvePoints;

    @Column(name = "reviewer_id", nullable = false)
    private Integer reviewerId;

    @Column(name = "review_time", nullable = false)
    private LocalDateTime reviewTime;

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


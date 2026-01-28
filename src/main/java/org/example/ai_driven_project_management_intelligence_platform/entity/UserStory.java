package org.example.ai_driven_project_management_intelligence_platform.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_story")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_id", nullable = false)
    private Integer storyId;

    @Column(name = "sprint_id", nullable = false)
    private Integer sprintId;

    @Column(name = "story_title", nullable = false, length = 128)
    private String storyTitle;

    @Column(name = "story_desc", columnDefinition = "text")
    private String storyDesc;

    @Column(name = "priority", nullable = false, columnDefinition = "tinyint(1) DEFAULT 2")
    private Byte priority = 2;

    @Column(name = "story_points", nullable = false)
    private Byte storyPoints;

    @Column(name = "status", nullable = false, columnDefinition = "tinyint(1) DEFAULT 0")
    private Byte status = 0;

    @Column(name = "po_id", nullable = false)
    private Integer poId;

    @Column(name = "parent_story_id")
    private Integer parentStoryId;

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


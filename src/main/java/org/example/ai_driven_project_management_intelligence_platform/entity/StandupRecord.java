package org.example.ai_driven_project_management_intelligence_platform.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "standup_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandupRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id", nullable = false)
    private Integer recordId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "sprint_id", nullable = false)
    private Integer sprintId;

    @Column(name = "yesterday_done", nullable = false, columnDefinition = "text")
    private String yesterdayDone;

    @Column(name = "today_plan", nullable = false, columnDefinition = "text")
    private String todayPlan;

    @Column(name = "blocker_ids", length = 128)
    private String blockerIds;

    @Column(name = "meeting_duration")
    private Integer meetingDuration;

    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime;

    @PrePersist
    protected void onCreate() {
        if (createTime == null) {
            createTime = LocalDateTime.now();
        }
    }
}


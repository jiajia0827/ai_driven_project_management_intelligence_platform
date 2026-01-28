package org.example.ai_driven_project_management_intelligence_platform.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

//燃尽图统计中间表（可视化数据支撑）
@Entity
@Table(name = "burn_down_stat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BurnDownStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stat_id", nullable = false)
    private Integer statId;

    @Column(name = "sprint_id", nullable = false)
    private Integer sprintId;

    @Column(name = "stat_date", nullable = false)
    private LocalDate statDate;

    @Column(name = "total_workload", nullable = false, precision = 8, scale = 1)
    private BigDecimal totalWorkload;

    @Column(name = "remaining_workload", nullable = false, precision = 8, scale = 1)
    private BigDecimal remainingWorkload;

    @Column(name = "is_workday", nullable = false, columnDefinition = "tinyint(1) DEFAULT 1")
    private Byte isWorkday = 1;

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


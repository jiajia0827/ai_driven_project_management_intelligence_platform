package org.example.ai_driven_project_management_intelligence_platform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "sys_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    @NotNull
    @NonNull
    private Integer roleId;

    @Column(name = "role_name", nullable = false, length = 32, unique = true)
    private String roleName;

    @Column(name = "role_desc", length = 128)
    private String roleDesc;

    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    // 手动设置创建时间，因为JPA不会自动设置
    @PrePersist
    protected void onCreate() {
        if (createTime == null) {
            createTime = LocalDateTime.now();
        }
        if (updateTime == null) {
            updateTime = LocalDateTime.now();
        }
    }

    // 手动更新更新时间
    @PreUpdate
    protected void onUpdate() {
        updateTime = LocalDateTime.now();
    }
}

package org.example.ai_driven_project_management_intelligence_platform.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "sys_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    @NotNull
    @NonNull
    private Integer userId;

    @Column(name = "username", nullable = false, length = 32, unique = true)
    private String username;

    @Column(name = "password", nullable = false, length = 128)
    private String password;

    @Column(name = "real_name", nullable = false, length = 32)
    private String realName;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Column(name = "status", nullable = false, columnDefinition = "tinyint(1) DEFAULT 1")
    private Byte status = 1;

    /**
     * 角色名称（登录等场景通过 MyBatis 联表查询时填充，不参与 JPA 映射）
     */
    @Transient
    private String roleName;

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


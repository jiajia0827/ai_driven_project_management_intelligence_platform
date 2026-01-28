package org.example.ai_driven_project_management_intelligence_platform.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "sys_menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id", nullable = false)
    private Integer menuId;

    @Column(name = "menu_name", nullable = false, length = 64)
    private String menuName;

    @Column(name = "path", nullable = false, length = 128)
    private String path;

    @Column(name = "parent_id", nullable = false, columnDefinition = "int(11) DEFAULT 0")
    private Integer parentId = 0;

    @Column(name = "menu_type", nullable = false)
    private Byte menuType;

    @Column(name = "sort", nullable = false, columnDefinition = "tinyint(2) DEFAULT 0")
    private Byte sort = 0;

    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime;

    @PrePersist
    protected void onCreate() {
        if (createTime == null) {
            createTime = LocalDateTime.now();
        }
    }
}


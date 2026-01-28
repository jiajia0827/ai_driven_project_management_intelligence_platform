package org.example.ai_driven_project_management_intelligence_platform.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

//项目文档表
@Entity
@Table(name = "project_doc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id", nullable = false)
    private Integer docId;

    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(name = "doc_name", nullable = false, length = 128)
    private String docName;

    @Column(name = "doc_type", nullable = false, columnDefinition = "tinyint(1) DEFAULT 3")
    private Byte docType = 3;

    @Column(name = "file_path", nullable = false, length = 255)
    private String filePath;

    @Column(name = "file_size")
    private Integer fileSize;

    @Column(name = "uploader_id", nullable = false)
    private Integer uploaderId;

    @Column(name = "view_count", nullable = false)
    private Integer viewCount = 0;

    @Column(name = "is_collected", nullable = false, columnDefinition = "tinyint(1) DEFAULT 0")
    private Byte isCollected = 0;

    @Column(name = "upload_time", nullable = false, updatable = false)
    private LocalDateTime uploadTime;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    @PrePersist
    protected void onCreate() {
        if (uploadTime == null) {
            uploadTime = LocalDateTime.now();
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


package org.example.ai_driven_project_management_intelligence_platform.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * 项目列表响应DTO
 * 用于返回项目列表查询结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectListResponseDTO {

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 项目列表数据
     */
    private java.util.List<ProjectItem> list;

    /**
     * 项目列表项
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectItem {
        private Integer projectId;
        private String projectName;
        private String projectDesc;
        
        @JsonFormat(pattern = "yyyy-MM-dd")
        private java.time.LocalDate startTime;
        
        @JsonFormat(pattern = "yyyy-MM-dd")
        private java.time.LocalDate endTime;
        
        private Byte status;
        private String statusName;
        private String creatorName;
        
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;
    }
}
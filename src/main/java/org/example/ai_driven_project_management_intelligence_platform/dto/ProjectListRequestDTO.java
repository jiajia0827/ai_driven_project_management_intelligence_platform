package org.example.ai_driven_project_management_intelligence_platform.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 项目列表查询请求DTO
 * 用于接收项目列表查询的请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectListRequestDTO {

    /**
     * 项目状态：0 - 未开始，1 - 进行中，2 - 已完成，3 - 延期
     */
    private Integer status;

    /**
     * 项目名称模糊查询关键字
     */
    private String keyword;
}
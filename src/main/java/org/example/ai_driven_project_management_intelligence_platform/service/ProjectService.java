package org.example.ai_driven_project_management_intelligence_platform.service;

import org.example.ai_driven_project_management_intelligence_platform.dto.CreateProjectRequestDTO;
import org.example.ai_driven_project_management_intelligence_platform.dto.ProjectListResponseDTO;
import org.example.ai_driven_project_management_intelligence_platform.entity.Project;

/**
 * 项目服务接口
 * 定义项目相关的业务操作
 */
public interface ProjectService {

    /**
     * 创建新项目
     * @param request 创建项目请求DTO
     * @param userId 创建者用户ID
     * @return 创建成功的项目实体
     */
    Project createProject(CreateProjectRequestDTO request, Integer userId);

    /**
     * 查询当前用户参与的项目列表
     * @param userId 用户ID
     * @param status 项目状态
     * @param keyword 项目名称关键字
     * @return 项目列表响应DTO
     */
    ProjectListResponseDTO getProjectList(Integer userId, Integer status, String keyword);
}
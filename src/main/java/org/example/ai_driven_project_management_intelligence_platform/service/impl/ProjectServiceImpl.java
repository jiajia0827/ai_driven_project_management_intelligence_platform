package org.example.ai_driven_project_management_intelligence_platform.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.ai_driven_project_management_intelligence_platform.dto.CreateProjectRequestDTO;
import org.example.ai_driven_project_management_intelligence_platform.dto.ProjectListResponseDTO;
import org.example.ai_driven_project_management_intelligence_platform.entity.Project;
import org.example.ai_driven_project_management_intelligence_platform.exception.BusinessException;
import org.example.ai_driven_project_management_intelligence_platform.mapper.ProjectMapper;
import org.example.ai_driven_project_management_intelligence_platform.service.ProjectService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 项目服务实现类
 * 实现项目相关的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper projectMapper;

    /**
     * 创建新项目
     * @param request 创建项目请求DTO
     * @param userId 创建者用户ID
     * @return 创建成功的项目实体
     */
    @Override
    public Project createProject(CreateProjectRequestDTO request, Integer userId) {
        // 检查项目名称是否已存在
        Project existingProject = projectMapper.selectByName(request.getProjectName());
        if (existingProject != null) {
            throw new BusinessException(400, "项目名称已存在");
        }

        // 构建项目实体
        Project project = new Project();
        project.setProjectName(request.getProjectName());
        project.setProjectDesc(request.getProjectDesc());
        project.setStartTime(request.getStartTime().atStartOfDay()); // 转换为LocalDateTime
        
        // 设置结束时间，如果提供了的话
        if (request.getEndTime() != null) {
            project.setEndTime(request.getEndTime().atStartOfDay());
        }
        
        // 设置状态为0（初始状态）
        project.setStatus((byte) 0);
        project.setCreatorId(userId);
        project.setCreateTime(LocalDateTime.now());
        project.setUpdateTime(LocalDateTime.now());

        // 插入数据库
        int result = projectMapper.insert(project);
        if (result <= 0) {
            throw new BusinessException(500, "创建项目失败");
        }

        return project;
    }

    /**
     * 查询当前用户参与的项目列表
     * @param userId 用户ID
     * @param status 项目状态
     * @param keyword 项目名称关键字
     * @return 项目列表响应DTO
     */
    @Override
    public ProjectListResponseDTO getProjectList(Integer userId, Integer status, String keyword) {
        // 调用Mapper查询项目列表
        java.util.List<ProjectListResponseDTO.ProjectItem> projectItems = 
            projectMapper.selectProjectList(userId, status, keyword);
        
        // 计算总记录数
        Long total = (long) projectItems.size();
        
        return new ProjectListResponseDTO(total, projectItems);
    }
}
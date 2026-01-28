package org.example.ai_driven_project_management_intelligence_platform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.ai_driven_project_management_intelligence_platform.entity.Project;

/**
 * 项目数据访问接口
 * 提供对项目表的基本增删改查操作
 */
@Mapper
public interface ProjectMapper {

    /**
     * 插入新项目
     * @param project 项目实体
     * @return 影响行数
     */
    int insert(Project project);

    /**
     * 根据项目ID查询项目
     * @param projectId 项目ID
     * @return 项目实体
     */
    Project selectById(@Param("projectId") Integer projectId);

    /**
     * 根据项目名称查询项目
     * @param projectName 项目名称
     * @return 项目实体
     */
    Project selectByName(@Param("projectName") String projectName);

    /**
     * 查询项目列表（当前用户参与的项目）
     * @param userId 用户ID
     * @param status 项目状态
     * @param keyword 项目名称关键字
     * @return 项目列表
     */
    java.util.List<org.example.ai_driven_project_management_intelligence_platform.dto.ProjectListResponseDTO.ProjectItem> selectProjectList(
            @Param("userId") Integer userId,
            @Param("status") Integer status,
            @Param("keyword") String keyword);
}
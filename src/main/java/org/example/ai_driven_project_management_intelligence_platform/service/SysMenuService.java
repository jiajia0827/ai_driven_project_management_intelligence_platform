package org.example.ai_driven_project_management_intelligence_platform.service;

import org.example.ai_driven_project_management_intelligence_platform.dto.CurrentUserResponseDTO;

import java.util.List;

/**
 * 菜单相关服务
 */
public interface SysMenuService {

    /**
     * 根据角色 ID 获取菜单树
     */
    List<CurrentUserResponseDTO.MenuDTO> getMenuTreeByRoleId(Integer roleId);
}


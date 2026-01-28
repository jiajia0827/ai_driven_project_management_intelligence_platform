package org.example.ai_driven_project_management_intelligence_platform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.ai_driven_project_management_intelligence_platform.entity.SysMenu;

import java.util.List;

/**
 * 菜单表 Mapper
 */
@Mapper
public interface SysMenuMapper {

    /**
     * 根据角色 ID 查询该角色可访问的菜单列表
     */
    @Select("""
            SELECT m.menu_id, m.menu_name, m.path, m.parent_id, m.menu_type, m.sort, m.create_time
            FROM sys_menu m
            INNER JOIN sys_role_menu rm ON m.menu_id = rm.menu_id
            WHERE rm.role_id = #{roleId}
            ORDER BY m.parent_id, m.sort
            """)
    List<SysMenu> selectMenusByRoleId(@Param("roleId") Integer roleId);
}


package org.example.ai_driven_project_management_intelligence_platform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.ai_driven_project_management_intelligence_platform.entity.SysUser;

/**
 * 用户表 Mapper（MyBatis）
 */
@Mapper
public interface SysUserMapper {

    /**
     * 根据用户名查询用户，关联角色表获取 roleName。
     * MyBatis 配置 map-underscore-to-camel-case=true，r.role_name → roleName。
     */
    @Select("""
            SELECT u.user_id, u.username, u.password, u.real_name, u.phone, u.email,
                   u.role_id, u.status, u.create_time, u.update_time,
                   r.role_name
            FROM sys_user u
            LEFT JOIN sys_role r ON u.role_id = r.role_id
            WHERE u.username = #{username}
            """)
    SysUser selectByUsername(@Param("username") String username);
}

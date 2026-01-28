package org.example.ai_driven_project_management_intelligence_platform.repository;

import org.example.ai_driven_project_management_intelligence_platform.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 系统用户 JPA 仓储
 */
public interface SysUserRepository extends JpaRepository<SysUser, Integer> {

    /**
     * 根据用户名查询用户
     */
    Optional<SysUser> findByUsername(String username);
}

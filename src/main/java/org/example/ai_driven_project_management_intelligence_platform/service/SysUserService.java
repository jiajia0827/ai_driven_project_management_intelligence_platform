package org.example.ai_driven_project_management_intelligence_platform.service;

import org.example.ai_driven_project_management_intelligence_platform.dto.LoginResponseDTO;

/**
 * 系统用户服务接口
 */
public interface SysUserService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 明文密码
     * @return 登录响应
     */
    LoginResponseDTO login(String username, String password);
}


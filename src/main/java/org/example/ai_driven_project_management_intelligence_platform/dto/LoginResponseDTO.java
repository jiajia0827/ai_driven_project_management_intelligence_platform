package org.example.ai_driven_project_management_intelligence_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录响应 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    /**
     * JWT token 字符串
     */
    private String token;

    /**
     * 用户信息
     */
    private UserInfo userInfo;

    /**
     * 嵌套的用户信息对象
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo {

        private Integer userId;

        private String username;

        private String realName;

        private Integer roleId;

        private String roleName;

        private String phone;

        private String email;

        private Byte status;
    }
}


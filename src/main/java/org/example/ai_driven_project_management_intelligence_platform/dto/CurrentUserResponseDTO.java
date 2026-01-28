package org.example.ai_driven_project_management_intelligence_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前登录用户信息响应 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserResponseDTO {

    private Integer userId;
    private String username;
    private String realName;
    private Integer roleId;
    private String roleName;
    private String phone;
    private String email;
    private Byte status;

    /**
     * 该角色可访问的菜单树
     */
    private List<MenuDTO> menus = new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MenuDTO {
        private Integer menuId;
        private String menuName;
        private String path;
        private Integer parentId;
        private Byte menuType;
        private Byte sort;
        private List<MenuDTO> children = new ArrayList<>();
    }
}


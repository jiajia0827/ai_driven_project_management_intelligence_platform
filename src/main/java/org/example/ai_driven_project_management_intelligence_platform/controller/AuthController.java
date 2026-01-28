package org.example.ai_driven_project_management_intelligence_platform.controller;

import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import org.example.ai_driven_project_management_intelligence_platform.dto.CurrentUserResponseDTO;
import org.example.ai_driven_project_management_intelligence_platform.dto.LoginRequestDTO;
import org.example.ai_driven_project_management_intelligence_platform.dto.LoginResponseDTO;
import org.example.ai_driven_project_management_intelligence_platform.entity.Result;
import org.example.ai_driven_project_management_intelligence_platform.service.SysMenuService;
import org.example.ai_driven_project_management_intelligence_platform.service.SysUserService;
import org.example.ai_driven_project_management_intelligence_platform.util.JwtUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 认证相关接口
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final SysUserService sysUserService;
    private final SysMenuService sysMenuService;
    private final JwtUtil jwtUtil;

    public AuthController(SysUserService sysUserService,
                          SysMenuService sysMenuService,
                          JwtUtil jwtUtil) {
        this.sysUserService = sysUserService;
        this.sysMenuService = sysMenuService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 用户登录（获取身份令牌）
     *
     * @param request 登录请求参数
     * @return 统一响应，data 中包含 token 和 userInfo
     */
    @PostMapping("/login")
    public Result<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        LoginResponseDTO responseDTO = sysUserService.login(request.getUsername(), request.getPassword());
        return Result.success(responseDTO);
    }

    /**
     * 获取当前登录用户信息及权限菜单
     * <p>
     * 思路：1）从请求头 Authorization 取出 Bearer token；
     * 2）校验 token 未过期并解析出 userId/roleId/username 等；
     * 3）用 roleId 查该角色可访问菜单，在 Service 中组装为树形；
     * 4）封装为 CurrentUserResponseDTO 返回。
     * </p>
     *
     * @param authorization 请求头 Authorization: Bearer {token}
     * @return data 为当前用户信息 + menus 树
     */
    @GetMapping("/currentUser")
    public Result<CurrentUserResponseDTO> currentUser(
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        if (!StringUtils.hasText(authorization) || !authorization.startsWith("Bearer ")) {
            return Result.fail401();
        }
        String token = authorization.substring(7);
        if (jwtUtil.isTokenExpired(token)) {
            return Result.fail401();
        }

        Claims claims;
        try {
            claims = jwtUtil.getClaimsFromToken(token);
        } catch (Exception e) {
            return Result.fail401();
        }

        Integer userId = ((Number) claims.get("userId")).intValue();
        Integer roleId = ((Number) claims.get("roleId")).intValue();
        String username = (String) claims.get("username");
        String realName = (String) claims.get("realName");
        String roleName = (String) claims.get("roleName");
        String phone = (String) claims.get("phone");
        String email = (String) claims.get("email");
        Byte status = null;
        Object statusObj = claims.get("status");
        if (statusObj instanceof Number n) {
            status = n.byteValue();
        }

        List<CurrentUserResponseDTO.MenuDTO> menus = sysMenuService.getMenuTreeByRoleId(roleId);

        CurrentUserResponseDTO dto = new CurrentUserResponseDTO(
                userId,
                username,
                realName,
                roleId,
                roleName,
                phone,
                email,
                status,
                menus
        );

        return Result.success(dto);
    }
}

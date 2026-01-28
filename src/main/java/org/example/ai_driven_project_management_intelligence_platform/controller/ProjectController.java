package org.example.ai_driven_project_management_intelligence_platform.controller;

import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import org.example.ai_driven_project_management_intelligence_platform.dto.CreateProjectRequestDTO;
import org.example.ai_driven_project_management_intelligence_platform.dto.ProjectListResponseDTO;
import org.example.ai_driven_project_management_intelligence_platform.entity.Project;
import org.example.ai_driven_project_management_intelligence_platform.entity.Result;
import org.example.ai_driven_project_management_intelligence_platform.service.ProjectService;
import org.example.ai_driven_project_management_intelligence_platform.util.JwtUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 项目控制器
 * 提供项目相关的REST API接口
 */
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;
    private final JwtUtil jwtUtil;

    public ProjectController(ProjectService projectService, JwtUtil jwtUtil) {
        this.projectService = projectService;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 创建新项目
     * 权限：SM/PO
     *
     * @param request 创建项目请求参数
     * @param authorization 请求头中的授权token
     * @return 统一响应格式，data中包含创建的项目完整信息
     */
    @PostMapping
    public Result<Project> createProject(@Valid @RequestBody CreateProjectRequestDTO request,
                                         @RequestHeader(value = "Authorization", required = false) String authorization) {
        // 验证权限 - 检查是否为SM或PO角色
        if (!hasPermission(authorization)) {
            return Result.fail403(); // 无权限访问
        }

        // 从token中提取用户ID
        Integer userId = getUserIdFromToken(authorization);

        // 创建项目
        Project createdProject = projectService.createProject(request, userId);

        return Result.success(createdProject);
    }

    /**
     * 验证用户权限（SM/PO）
     * 
     * @param authorization 授权头
     * @return 是否有权限
     */
    private boolean hasPermission(String authorization) {
        if (!StringUtils.hasText(authorization) || !authorization.startsWith("Bearer ")) {
            return false;
        }
        
        String token = authorization.substring(7);
        if (jwtUtil.isTokenExpired(token)) {
            return false;
        }

        try {
            Claims claims = jwtUtil.getClaimsFromToken(token);
            Number roleIdNumber = (Number) claims.get("roleId");
            if (roleIdNumber == null) {
                System.out.println("DEBUG: roleId is null");
                return false;
            }
            int roleId = roleIdNumber.intValue();
            System.out.println("DEBUG: roleId from token = " + roleId);
            
            // 允许PO(1)或SM(2)角色创建项目，可根据实际业务调整
            boolean hasPermission = roleId == 1 || roleId == 2;
            System.out.println("DEBUG: hasPermission = " + hasPermission + " for roleId = " + roleId);
            return hasPermission;
        } catch (Exception e) {
            System.out.println("DEBUG: Exception in permission check: " + e.getMessage());
            return false;
        }
    }

    /**
     * 从Token中提取用户ID
     * 
     * @param authorization 授权头
     * @return 用户ID
     */
    private Integer getUserIdFromToken(String authorization) {
        String token = authorization.substring(7);
        Claims claims = jwtUtil.getClaimsFromToken(token);
        return ((Number) claims.get("userId")).intValue();
    }

    /**
     * 查询当前用户参与的项目列表
     * 权限：所有登录用户
     *
     * @param status 项目状态
     * @param keyword 项目名称关键字
     * @param authorization 请求头中的授权token
     * @return 统一响应格式，data中包含项目列表和总数
     */
    @GetMapping("/list")
    public Result<ProjectListResponseDTO> getProjectList(
            Integer status,
            String keyword,
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        // 验证权限 - 检查用户是否已登录
        if (!hasPermission(authorization)) {
            return Result.fail401();
        }

        // 从token中提取用户ID
        Integer userId = getUserIdFromToken(authorization);

        // 查询项目列表
        ProjectListResponseDTO projectList = projectService.getProjectList(userId, status, keyword);

        return Result.success(projectList);
    }
}
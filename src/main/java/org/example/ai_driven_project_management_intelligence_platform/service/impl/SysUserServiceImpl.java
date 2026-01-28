package org.example.ai_driven_project_management_intelligence_platform.service.impl;

import org.example.ai_driven_project_management_intelligence_platform.dto.LoginResponseDTO;
import org.example.ai_driven_project_management_intelligence_platform.entity.SysUser;
import org.example.ai_driven_project_management_intelligence_platform.exception.PasswordMismatchException;
import org.example.ai_driven_project_management_intelligence_platform.exception.UserNotFoundException;
import org.example.ai_driven_project_management_intelligence_platform.mapper.SysUserMapper;
import org.example.ai_driven_project_management_intelligence_platform.service.SysUserService;
import org.example.ai_driven_project_management_intelligence_platform.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统用户服务实现（登录等读操作使用 MyBatis Mapper）
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper sysUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public SysUserServiceImpl(SysUserMapper sysUserMapper,
                              PasswordEncoder passwordEncoder,
                              JwtUtil jwtUtil) {
        this.sysUserMapper = sysUserMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponseDTO login(String username, String password) {
        // 1. 根据用户名查询用户（MyBatis 联表查 roleName）
        SysUser user = sysUserMapper.selectByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("用户不存在");
        }

        // 2. 校验密码（BCryptPasswordEncoder.matches）
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordMismatchException("密码错误");
        }

        // 3. 生成 JWT Token，包含用户核心身份信息
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("username", user.getUsername());
        claims.put("realName", user.getRealName());
        claims.put("roleId", user.getRoleId());
        claims.put("roleName", user.getRoleName());
        claims.put("phone", user.getPhone());
        claims.put("email", user.getEmail());
        claims.put("status", user.getStatus());

        String token = jwtUtil.generateToken(claims);

        // 4. 组装响应 DTO
        LoginResponseDTO.UserInfo userInfo = new LoginResponseDTO.UserInfo(
                user.getUserId(),
                user.getUsername(),
                user.getRealName(),
                user.getRoleId(),
                user.getRoleName(),
                user.getPhone(),
                user.getEmail(),
                user.getStatus()
        );

        return new LoginResponseDTO(token, userInfo);
    }
}


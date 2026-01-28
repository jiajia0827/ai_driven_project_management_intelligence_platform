package org.example.ai_driven_project_management_intelligence_platform.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * JWT 配置类，从配置文件读取密钥和过期时间
 */
@Configuration
@Data
public class JwtConfig {

    /**
     * 签名密钥
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * 过期时间（秒）
     */
    @Value("${jwt.expiration:7200}")
    private long expirationInSeconds;
}


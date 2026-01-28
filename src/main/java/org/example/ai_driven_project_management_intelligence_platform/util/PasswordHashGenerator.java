package org.example.ai_driven_project_management_intelligence_platform.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * 生成 BCrypt 密码的工具（用于重置/初始化账号）
 *
 * 用法（IDE 直接运行 main）：
 * - 不传参：默认生成 "123456"
 * - 传参：args[0] 作为明文密码
 */
public class PasswordHashGenerator {

    public static void main(String[] args) {
        String raw = (args != null && args.length > 0) ? args[0] : "123456";
        String hash = BCrypt.hashpw(raw, BCrypt.gensalt(10));
        System.out.println(hash);
    }
}


package org.example.ai_driven_project_management_intelligence_platform.exception;

/**
 * 用户不存在异常
 */
public class UserNotFoundException extends BusinessException {

    private static final int DEFAULT_CODE = 40401;

    public UserNotFoundException(String msg) {
        super(DEFAULT_CODE, msg);
    }
}


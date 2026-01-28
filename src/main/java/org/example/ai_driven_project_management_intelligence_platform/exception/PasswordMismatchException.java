package org.example.ai_driven_project_management_intelligence_platform.exception;

/**
 * 密码错误异常
 */
public class PasswordMismatchException extends BusinessException {

    private static final int DEFAULT_CODE = 401;

    public PasswordMismatchException(String msg) {
        super(DEFAULT_CODE, msg);
    }
}


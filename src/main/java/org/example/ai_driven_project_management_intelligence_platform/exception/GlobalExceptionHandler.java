package org.example.ai_driven_project_management_intelligence_platform.exception;


import org.example.ai_driven_project_management_intelligence_platform.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义业务异常
     *
     * @param e 业务异常
     * @return 统一响应结果
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Object> handleBusinessException(BusinessException e) {
        logger.error("业务异常: {}", e.getMsg(), e);
        return Result.fail(e.getCode(), e.getMsg());
    }

    /**
     * 处理空指针异常
     *
     * @param e 空指针异常
     * @return 统一响应结果
     */
    @ExceptionHandler(NullPointerException.class)
    public Result<Object> handleNullPointerException(NullPointerException e) {
        logger.error("空指针异常: {}", e.getMessage(), e);
        return Result.fail500();
    }

    /**
     * 处理参数非法异常
     *
     * @param e 参数非法异常
     * @return 统一响应结果
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        logger.error("参数非法异常: {}", e.getMessage(), e);
        return Result.fail(400, e.getMessage());
    }

    /**
     * 处理系统异常
     *
     * @param e 系统异常
     * @return 统一响应结果
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e) {
        logger.error("服务器内部异常: {}", e.getMessage(), e);
        return Result.fail500();
    }
}


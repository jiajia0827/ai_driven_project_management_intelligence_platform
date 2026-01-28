package org.example.ai_driven_project_management_intelligence_platform.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 统一响应结果类
 *
 * @param <T> 响应数据类型
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    // Getter and Setter methods
    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应描述信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public Result() {}

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功响应（无数据）
     *
     * @return 成功响应对象
     */
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功");
    }

    /**
     * 成功响应（带数据）
     *
     * @param data 响应数据
     * @return 成功响应对象
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    /**
     * 失败响应（自定义错误码和消息）
     *
     * @param code 错误码
     * @param msg  错误消息
     * @return 失败响应对象
     */
    public static <T> Result<T> fail(Integer code, String msg) {
        return new Result<>(code, msg);
    }

    /**
     * 未登录/登录失效响应
     *
     * @return 未登录响应对象
     */
    public static <T> Result<T> fail401() {
        return new Result<>(401, "未登录或登录已失效");
    }

    /**
     * 无权限访问响应
     *
     * @return 无权限响应对象
     */
    public static <T> Result<T> fail403() {
        return new Result<>(403, "无权限访问");
    }

    /**
     * 资源不存在响应
     *
     * @return 资源不存在响应对象
     */
    public static <T> Result<T> fail404() {
        return new Result<>(404, "资源不存在");
    }

    /**
     * 服务端异常响应
     *
     * @return 服务端异常响应对象
     */
    public static <T> Result<T> fail500() {
        return new Result<>(500, "服务端异常");
    }

}


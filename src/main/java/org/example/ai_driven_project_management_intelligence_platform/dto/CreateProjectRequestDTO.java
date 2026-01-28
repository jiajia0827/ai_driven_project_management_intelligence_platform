package org.example.ai_driven_project_management_intelligence_platform.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

/**
 * 创建项目请求DTO
 * 用于接收创建项目的请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectRequestDTO {

    /**
     * 项目名称，必填，长度不超过64字符，唯一
     */
    @NotBlank(message = "项目名称不能为空")
    private String projectName;

    /**
     * 项目描述，可选
     */
    private String projectDesc;

    /**
     * 项目开始时间，必填
     */
    @NotNull(message = "项目开始时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;

    /**
     * 项目预计结束时间，可选
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;
}
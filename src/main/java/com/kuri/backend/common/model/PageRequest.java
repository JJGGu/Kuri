package com.kuri.backend.common.model;

import lombok.Data;

import jakarta.validation.constraints.Min;

/**
 * 分页请求类
 */
@Data
public class PageRequest {
    
    /**
     * 当前页码
     */
    @Min(value = 1, message = "页码必须大于0")
    private Integer pageNum = 1;
    
    /**
     * 每页数量
     */
    @Min(value = 1, message = "每页条数必须大于0")
    private Integer pageSize = 10;
} 
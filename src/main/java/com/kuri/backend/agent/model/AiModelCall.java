package com.kuri.backend.agent.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * AI模型调用记录实体类
 */
@Data
public class AiModelCall {
    /**
     * 调用记录ID
     */
    private Long id;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 关联行程ID
     */
    private Long tripId;
    
    /**
     * 调用类型：trip_generate/trip_update/day_generate
     */
    private String callType;
    
    /**
     * 模型名称
     */
    private String modelName;
    
    /**
     * 输入提示词
     */
    private String prompt;
    
    /**
     * AI返回结果
     */
    private String response;
    
    /**
     * 状态：0处理中，1成功，2失败
     */
    private Integer status;
    
    /**
     * 错误信息
     */
    private String errorMessage;
    
    /**
     * 使用的token数量
     */
    private Integer tokensUsed;
    
    /**
     * 调用费用
     */
    private BigDecimal cost;
    
    /**
     * 调用耗时(毫秒)
     */
    private Integer durationMs;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
} 
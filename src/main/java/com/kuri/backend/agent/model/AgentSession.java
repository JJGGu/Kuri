package com.kuri.backend.agent.model;

import lombok.Data;

import java.util.Date;

/**
 * AI助手会话实体类
 */
@Data
public class AgentSession {
    /**
     * 会话ID
     */
    private Long id;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 会话唯一标识
     */
    private String sessionId;
    
    /**
     * 会话标题
     */
    private String title;
    
    /**
     * 状态（0：关闭，1：活跃）
     */
    private Integer status;
    
    /**
     * 创建时间
     */
    private Date createdAt;
    
    /**
     * 更新时间
     */
    private Date updatedAt;
} 
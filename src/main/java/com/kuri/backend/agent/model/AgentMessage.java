package com.kuri.backend.agent.model;

import lombok.Data;

import java.util.Date;

/**
 * AI助手消息实体类
 */
@Data
public class AgentMessage {
    /**
     * 消息ID
     */
    private Long id;
    
    /**
     * 会话ID
     */
    private Long sessionId;
    
    /**
     * 消息类型（0：用户消息，1：助手消息）
     */
    private Integer messageType;
    
    /**
     * 消息内容
     */
    private String content;
    
    /**
     * 创建时间
     */
    private Date createdAt;
} 
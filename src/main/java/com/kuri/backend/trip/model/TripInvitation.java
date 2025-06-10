package com.kuri.backend.trip.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 行程邀请实体类
 */
@Data
public class TripInvitation {
    /**
     * 邀请ID
     */
    private Long id;
    
    /**
     * 行程ID
     */
    private Long tripId;
    
    /**
     * 邀请人ID
     */
    private Long inviterUserId;
    
    /**
     * 被邀请用户ID（如果已注册）
     */
    private Long inviteeUserId;
    
    /**
     * 被邀请人手机号
     */
    private String inviteePhone;
    
    /**
     * 邀请角色：Editor/Viewer
     */
    private String role;
    
    /**
     * 邀请消息
     */
    private String message;
    
    /**
     * 状态：Pending/Accepted/Rejected/Expired
     */
    private String status;
    
    /**
     * 过期时间
     */
    private LocalDateTime expiresAt;
    
    /**
     * 处理时间
     */
    private LocalDateTime processedAt;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
} 
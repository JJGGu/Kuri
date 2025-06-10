package com.kuri.backend.trip.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 行程协作者实体类
 */
@Data
public class TripCollaborator {
    /**
     * 协作记录ID
     */
    private Long id;
    
    /**
     * 行程ID
     */
    private Long tripId;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 角色：Owner/Editor/Viewer
     */
    private String role;
    
    /**
     * 状态：Active/Pending/Inactive
     */
    private String status;
    
    /**
     * 权限配置（JSON格式）
     */
    private String permissions;
    
    /**
     * 邀请人ID
     */
    private Long invitedBy;
    
    /**
     * 邀请时间
     */
    private LocalDateTime invitedAt;
    
    /**
     * 加入时间
     */
    private LocalDateTime joinedAt;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
} 
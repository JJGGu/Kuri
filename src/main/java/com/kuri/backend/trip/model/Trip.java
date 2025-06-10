package com.kuri.backend.trip.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 旅行实体类
 */
@Data
public class Trip {
    /**
     * 旅行ID
     */
    private Long id;
    
    /**
     * 创建用户ID
     */
    private Long ownerUserId;
    
    /**
     * 行程标题
     */
    private String title;
    
    /**
     * 行程描述
     */
    private String description;
    
    /**
     * 封面图片
     */
    private String coverImage;
    
    /**
     * 出发地POI ID
     */
    private String departureLocationId;
    
    /**
     * 出发地名称
     */
    private String departureName;
    
    /**
     * 目的地POI ID列表（JSON格式）
     */
    private String destinationLocationIds;
    
    /**
     * 目的地名称列表（JSON格式）
     */
    private String destinationNames;
    
    /**
     * 开始日期
     */
    private Date startDate;
    
    /**
     * 结束日期
     */
    private Date endDate;
    
    /**
     * 行程天数
     */
    private Integer days;
    
    /**
     * 交通方式：Driving/PublicTransport
     */
    private String travelMode;
    
    /**
     * 偏好列表（JSON格式）
     */
    private String preferences;
    
    /**
     * 预算
     */
    private BigDecimal budget;
    
    /**
     * 预估费用
     */
    private BigDecimal estimatedCost;
    
    /**
     * 状态：Planning/Ongoing/Completed/Cancelled
     */
    private String status;
    
    /**
     * 是否公开
     */
    private Boolean isPublic;
    
    /**
     * 生成状态：0未生成，1生成中，2已生成，3生成失败
     */
    private Integer generationStatus;
    
    /**
     * 浏览次数
     */
    private Integer viewCount;
    
    /**
     * 点赞次数
     */
    private Integer likeCount;
    
    /**
     * 分享次数
     */
    private Integer shareCount;
    
    /**
     * 标签列表（JSON格式）
     */
    private String tags;
    
    /**
     * 天气信息（JSON格式）
     */
    private String weatherInfo;
    
    /**
     * 行程风格：Relaxed/Adventure/Family/Luxury/Culture/Nature/Custom
     */
    private String style;
    
    /**
     * 出行人数
     */
    private Integer peopleCount;
    
    /**
     * 创建时间
     */
    private Date createdAt;
    
    /**
     * 更新时间
     */
    private Date updatedAt;
    
    /**
     * 删除时间（软删除）
     */
    private Date deletedAt;
} 
package com.kuri.backend.guide.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 攻略实体类
 */
@Data
public class Guide {
    /**
     * 攻略唯一ID
     */
    private Long id;
    
    /**
     * 攻略标题
     */
    private String title;
    
    /**
     * 原始链接
     */
    private String url;
    
    /**
     * 封面图片URL
     */
    private String coverImage;
    
    /**
     * 攻略摘要/描述
     */
    private String description;
    
    /**
     * 攻略类型（Food/Hotel/Attraction/Other）
     */
    private String type;
    
    /**
     * 平台：xiaohongshu/ctrip/mafengwo/dianping/qunar/meituan/official/user
     */
    private String platform;
    
    /**
     * 关联城市
     */
    private String city;
    
    /**
     * 景点列表（结构化JSON）
     */
    private String attractions;
    
    /**
     * 美食列表（结构化JSON）
     */
    private String foods;
    
    /**
     * 住宿列表（结构化JSON）
     */
    private String hotels;
    
    /**
     * 涉及POI ID列表
     */
    private String poiIds;
    
    /**
     * 正文内容
     */
    private String content;
    
    /**
     * 标签
     */
    private String tags;
    
    /**
     * 创建用户ID（如为用户上传）
     */
    private Long createdBy;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
    
    /**
     * 删除时间（软删除）
     */
    private LocalDateTime deletedAt;
} 
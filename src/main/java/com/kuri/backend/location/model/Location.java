package com.kuri.backend.location.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 地点实体类
 */
@Data
public class Location implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 地点ID
     */
    private Long id;
    
    /**
     * 地点名称
     */
    private String name;
    
    /**
     * 地点描述
     */
    private String description;
    
    /**
     * 详细地址
     */
    private String address;
    
    /**
     * 纬度
     */
    private Double latitude;
    
    /**
     * 经度
     */
    private Double longitude;
    
    /**
     * 图片URL
     */
    private String imageUrl;
    
    /**
     * 分类
     */
    private String category;
    
    /**
     * 所在城市
     */
    private String city;
    
    /**
     * 所在省份
     */
    private String province;
    
    /**
     * 所在国家
     */
    private String country;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
    
    /**
     * 是否激活
     */
    private Boolean isActive;
    
    /**
     * 浏览次数
     */
    private Integer viewCount;
    
    /**
     * 评分
     */
    private Double rating;
} 
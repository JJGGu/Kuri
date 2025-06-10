package com.kuri.backend.location.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 景点详情实体类
 */
@Data
public class AttractionDetail {
    /**
     * 景点详情ID
     */
    private Long id;
    
    /**
     * 景点名称
     */
    private String name;
    
    /**
     * 景点分类：自然风光/历史文化/主题乐园/博物馆等
     */
    private String category;
    
    /**
     * 景点等级：5A/4A/3A等
     */
    private String level;
    
    /**
     * 景点描述
     */
    private String description;
    
    /**
     * 景点亮点
     */
    private String highlights;
    
    /**
     * 历史背景
     */
    private String history;
    
    /**
     * 最佳游览时间
     */
    private String bestVisitTime;
    
    /**
     * 建议游览时长
     */
    private String suggestedDuration;
    
    /**
     * 详细地址
     */
    private String address;
    
    /**
     * 所在城市
     */
    private String city;
    
    /**
     * 纬度
     */
    private BigDecimal latitude;
    
    /**
     * 经度
     */
    private BigDecimal longitude;
    
    /**
     * 高德POI ID
     */
    private String amapPoiId;
    
    /**
     * 门票价格
     */
    private BigDecimal ticketPrice;
    
    /**
     * 门票详细信息（JSON格式）
     */
    private String ticketInfo;
    
    /**
     * 预订链接
     */
    private String bookingUrl;
    
    /**
     * 营业时间
     */
    private String businessHours;
    
    /**
     * 季节性营业时间（JSON格式）
     */
    private String seasonalHours;
    
    /**
     * 联系电话
     */
    private String contactPhone;
    
    /**
     * 官方网站
     */
    private String officialWebsite;
    
    /**
     * 评分
     */
    private BigDecimal rating;
    
    /**
     * 评价数量
     */
    private Integer reviewCount;
    
    /**
     * 封面图片
     */
    private String coverImage;
    
    /**
     * 图片列表（JSON格式）
     */
    private String images;
    
    /**
     * 视频列表（JSON格式）
     */
    private String videos;
    
    /**
     * 设施列表：停车场/餐厅/商店/无障碍设施等（JSON格式）
     */
    private String facilities;
    
    /**
     * 服务列表：导游/讲解器/行李寄存等（JSON格式）
     */
    private String services;
    
    /**
     * 交通指南
     */
    private String transportationGuide;
    
    /**
     * 停车信息
     */
    private String parkingInfo;
    
    /**
     * 推荐游览路线（JSON格式）
     */
    private String tourRoutes;
    
    /**
     * 游览贴士
     */
    private String tips;
    
    /**
     * 游览限制
     */
    private String restrictions;
    
    /**
     * 标签列表（JSON格式）
     */
    private String tags;
    
    /**
     * 数据来源：amap/manual/crawl
     */
    private String dataSource;
    
    /**
     * 最后验证时间
     */
    private LocalDateTime lastVerifiedAt;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
} 
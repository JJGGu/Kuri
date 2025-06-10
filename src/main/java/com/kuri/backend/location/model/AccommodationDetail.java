package com.kuri.backend.location.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 酒店详情实体类
 */
@Data
public class AccommodationDetail {
    /**
     * 酒店详情ID
     */
    private Long id;
    
    /**
     * 高德POI ID
     */
    private String amapPoiId;
    
    /**
     * 酒店名称
     */
    private String name;
    
    /**
     * 酒店品牌
     */
    private String brand;
    
    /**
     * 酒店类型：豪华酒店/商务酒店/经济酒店/民宿/青旅等
     */
    private String category;
    
    /**
     * 星级：五星/四星/三星等
     */
    private String starLevel;
    
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
     * 酒店描述
     */
    private String description;
    
    /**
     * 酒店特色
     */
    private String features;
    
    /**
     * 价格区间
     */
    private String priceRange;
    
    /**
     * 平均价格
     */
    private BigDecimal averagePrice;
    
    /**
     * 预订平台信息（JSON格式）
     */
    private String bookingPlatforms;
    
    /**
     * 入住时间
     */
    private String checkInTime;
    
    /**
     * 退房时间
     */
    private String checkOutTime;
    
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
     * 清洁度评分
     */
    private BigDecimal cleanlinessScore;
    
    /**
     * 服务评分
     */
    private BigDecimal serviceScore;
    
    /**
     * 位置评分
     */
    private BigDecimal locationScore;
    
    /**
     * 封面图片
     */
    private String coverImage;
    
    /**
     * 图片列表（JSON格式）
     */
    private String images;
    
    /**
     * 客房图片（JSON格式）
     */
    private String roomImages;
    
    /**
     * 酒店设施：WiFi/停车场/健身房/游泳池/餐厅等（JSON格式）
     */
    private String facilities;
    
    /**
     * 客房设施：空调/电视/冰箱/吹风机等（JSON格式）
     */
    private String roomFacilities;
    
    /**
     * 服务项目：24小时前台/行李寄存/洗衣服务等（JSON格式）
     */
    private String services;
    
    /**
     * 房型列表（JSON格式）
     */
    private String roomTypes;
    
    /**
     * 酒店政策：取消政策/宠物政策/儿童政策等（JSON格式）
     */
    private String policies;
    
    /**
     * 交通信息
     */
    private String transportationInfo;
    
    /**
     * 周边景点（JSON格式）
     */
    private String nearbyAttractions;
    
    /**
     * 标签列表（JSON格式）
     */
    private String tags;
    
    /**
     * 数据来源：amap/booking/manual
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
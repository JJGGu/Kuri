package com.kuri.backend.location.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 美食详情实体类
 */
@Data
public class FoodDetail {
    /**
     * 美食详情ID
     */
    private Long id;
    
    /**
     * 高德POI ID
     */
    private String amapPoiId;
    
    /**
     * 餐厅名称
     */
    private String name;
    
    /**
     * 菜系：川菜/粤菜/西餐/日料/韩料等
     */
    private String cuisineType;
    
    /**
     * 餐厅类型：正餐/快餐/咖啡厅/酒吧/小吃等
     */
    private String category;
    
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
     * 餐厅描述
     */
    private String description;
    
    /**
     * 招牌菜
     */
    private String specialties;
    
    /**
     * 餐厅氛围
     */
    private String atmosphere;
    
    /**
     * 价格等级：经济实惠/中等价位/高档消费
     */
    private String priceLevel;
    
    /**
     * 人均消费
     */
    private BigDecimal averageCost;
    
    /**
     * 营业时间
     */
    private String businessHours;
    
    /**
     * 休息时间
     */
    private String breakTime;
    
    /**
     * 联系电话
     */
    private String contactPhone;
    
    /**
     * 评分
     */
    private BigDecimal rating;
    
    /**
     * 评价数量
     */
    private Integer reviewCount;
    
    /**
     * 口味评分
     */
    private BigDecimal tasteScore;
    
    /**
     * 环境评分
     */
    private BigDecimal environmentScore;
    
    /**
     * 服务评分
     */
    private BigDecimal serviceScore;
    
    /**
     * 封面图片
     */
    private String coverImage;
    
    /**
     * 图片列表（JSON格式）
     */
    private String images;
    
    /**
     * 菜单图片（JSON格式）
     */
    private String menuImages;
    
    /**
     * 菜单项目（JSON格式）
     */
    private String menuItems;
    
    /**
     * 推荐菜品（JSON格式）
     */
    private String recommendedDishes;
    
    /**
     * 设施：包厢/WiFi/停车位/儿童座椅等（JSON格式）
     */
    private String facilities;
    
    /**
     * 服务：外卖/预订/刷卡/移动支付等（JSON格式）
     */
    private String services;
    
    /**
     * 饮食选择：素食/清真/无麸质等（JSON格式）
     */
    private String dietaryOptions;
    
    /**
     * 适合场合：商务宴请/家庭聚餐/约会等（JSON格式）
     */
    private String suitableOccasions;
    
    /**
     * 是否需要预订
     */
    private Boolean reservationRequired;
    
    /**
     * 预订平台（JSON格式）
     */
    private String bookingPlatforms;
    
    /**
     * 标签列表（JSON格式）
     */
    private String tags;
    
    /**
     * 数据来源：amap/dianping/manual
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
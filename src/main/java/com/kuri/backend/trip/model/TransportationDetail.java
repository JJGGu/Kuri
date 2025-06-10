package com.kuri.backend.trip.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 交通详情实体类
 */
@Data
public class TransportationDetail {
    /**
     * 交通详情ID
     */
    private Long id;
    
    /**
     * 交通方式：Walking/Driving/PublicTransport/Flight/Train/Taxi
     */
    private String mode;
    
    /**
     * 服务提供商：高德/滴滴/12306/航空公司
     */
    private String provider;
    
    /**
     * 起点名称
     */
    private String fromName;
    
    /**
     * 起点纬度
     */
    private BigDecimal fromLatitude;
    
    /**
     * 起点经度
     */
    private BigDecimal fromLongitude;
    
    /**
     * 起点POI ID
     */
    private String fromPoiId;
    
    /**
     * 终点名称
     */
    private String toName;
    
    /**
     * 终点纬度
     */
    private BigDecimal toLatitude;
    
    /**
     * 终点经度
     */
    private BigDecimal toLongitude;
    
    /**
     * 终点POI ID
     */
    private String toPoiId;
    
    /**
     * 距离(km)
     */
    private BigDecimal distance;
    
    /**
     * 预计时长(分钟)
     */
    private Integer duration;
    
    /**
     * 路线描述
     */
    private String routeDescription;
    
    /**
     * 详细路线步骤（JSON格式）
     */
    private String routeSteps;
    
    /**
     * 基础费用
     */
    private BigDecimal baseCost;
    
    /**
     * 预估总费用
     */
    private BigDecimal estimatedCost;
    
    /**
     * 费用明细（JSON格式）
     */
    private String costBreakdown;
    
    /**
     * 出发时间
     */
    private LocalTime departureTime;
    
    /**
     * 到达时间
     */
    private LocalTime arrivalTime;
    
    /**
     * 班次信息（JSON格式）
     */
    private String scheduleInfo;
    
    /**
     * 是否需要预订
     */
    private Boolean bookingRequired;
    
    /**
     * 预订链接
     */
    private String bookingUrl;
    
    /**
     * 预订平台列表（JSON格式）
     */
    private String bookingPlatforms;
    
    /**
     * 导航链接
     */
    private String navigationUrl;
    
    /**
     * 高德路线ID
     */
    private String amapRouteId;
    
    /**
     * 出行提示
     */
    private String tips;
    
    /**
     * 限制说明
     */
    private String restrictions;
    
    /**
     * 数据来源：amap/baidu/manual
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
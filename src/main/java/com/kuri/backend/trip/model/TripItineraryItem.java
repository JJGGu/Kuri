package com.kuri.backend.trip.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

/**
 * 行程项目实体类
 */
@Data
public class TripItineraryItem {
    /**
     * 行程项目ID
     */
    private Long id;
    
    /**
     * 行程ID
     */
    private Long tripId;
    
    /**
     * 日程ID
     */
    private Long dayId;
    
    /**
     * 第几天
     */
    private Integer dayIndex;
    
    /**
     * 当天顺序
     */
    private Integer itemOrder;
    
    /**
     * 类型：Attraction/Accommodation/Food/Transportation
     */
    private String type;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 描述
     */
    private String description;
    
    /**
     * 开始时间
     */
    private Time startTime;
    
    /**
     * 结束时间
     */
    private Time endTime;
    
    /**
     * 时长(分钟)
     */
    private Integer duration;
    
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
     * 预估费用
     */
    private BigDecimal estimatedCost;
    
    /**
     * 详情表ID（根据type关联不同表）
     */
    private Long detailId;
    
    /**
     * 备注
     */
    private String notes;
    
    /**
     * 状态：Active/Cancelled/Completed
     */
    private String status;
    
    /**
     * 创建时间
     */
    private Date createdAt;
    
    /**
     * 更新时间
     */
    private Date updatedAt;
} 
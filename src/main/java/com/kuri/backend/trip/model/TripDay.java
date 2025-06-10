package com.kuri.backend.trip.model;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

/**
 * 行程日程实体类
 */
@Data
public class TripDay {
    /**
     * 日程ID
     */
    private Long id;
    
    /**
     * 行程ID
     */
    private Long tripId;
    
    /**
     * 第几天
     */
    private Integer dayIndex;
    
    /**
     * 具体日期
     */
    private Date date;
    
    /**
     * 当天标题
     */
    private String title;
    
    /**
     * 当天概述
     */
    private String summary;
    
    /**
     * 当天所在城市
     */
    private String city;
    
    /**
     * 当天主题
     */
    private String theme;
    
    /**
     * 天气状况
     */
    private String weatherCondition;
    
    /**
     * 温度范围
     */
    private String weatherTemperature;
    
    /**
     * 湿度
     */
    private String weatherHumidity;
    
    /**
     * 风力
     */
    private String weatherWind;
    
    /**
     * 起点名称
     */
    private String startLocationName;
    
    /**
     * 起点时间
     */
    private Time startLocationTime;
    
    /**
     * 起点POI ID
     */
    private String startLocationPoiId;
    
    /**
     * 终点名称
     */
    private String endLocationName;
    
    /**
     * 终点时间
     */
    private Time endLocationTime;
    
    /**
     * 终点POI ID
     */
    private String endLocationPoiId;
    
    /**
     * 当日预估费用
     */
    private BigDecimal estimatedCost;
    
    /**
     * 是否已生成详细行程
     */
    private Boolean isGenerated;
    
    /**
     * 创建时间
     */
    private Date createdAt;
    
    /**
     * 更新时间
     */
    private Date updatedAt;
} 
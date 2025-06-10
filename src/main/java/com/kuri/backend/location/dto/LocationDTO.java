package com.kuri.backend.location.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 位置DTO类
 */
@Data
public class LocationDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 位置ID
     */
    private Long id;
    
    /**
     * 位置名称
     */
    private String name;
    
    /**
     * 位置类型（1：景点，2：餐厅，3：酒店，4：购物，5：娱乐）
     */
    private Integer type;
    
    /**
     * 位置类型名称
     */
    private String typeName;
    
    /**
     * 位置描述
     */
    private String description;
    
    /**
     * 地址
     */
    private String address;
    
    /**
     * 经度
     */
    private Double longitude;
    
    /**
     * 纬度
     */
    private Double latitude;
    
    /**
     * 城市
     */
    private String city;
    
    /**
     * 省份/州
     */
    private String province;
    
    /**
     * 国家
     */
    private String country;
    
    /**
     * 联系电话
     */
    private String phone;
    
    /**
     * 网站
     */
    private String website;
    
    /**
     * 营业时间
     */
    private String openingHours;
    
    /**
     * 评分
     */
    private Double rating;
    
    /**
     * 评分人数
     */
    private Integer ratingCount;
    
    /**
     * 图片URL
     */
    private String imageUrl;
} 
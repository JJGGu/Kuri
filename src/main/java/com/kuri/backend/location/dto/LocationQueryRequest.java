package com.kuri.backend.location.dto;

import com.kuri.backend.common.model.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 位置查询请求类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LocationQueryRequest extends PageRequest {
    
    /**
     * 位置名称
     */
    private String name;
    
    /**
     * 位置类型
     */
    private Integer type;
    
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
     * 最小评分
     */
    private Double minRating;
} 
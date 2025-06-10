package com.kuri.backend.location.service;

import com.kuri.backend.common.model.PageResult;
import com.kuri.backend.location.dto.LocationDTO;
import com.kuri.backend.location.dto.LocationQueryRequest;
import com.kuri.backend.location.model.Location;

import java.util.List;

/**
 * 位置服务接口
 */
public interface LocationService {
    
    /**
     * 根据ID查询位置
     *
     * @param id 位置ID
     * @return 位置DTO
     */
    LocationDTO getLocationById(Long id);
    
    /**
     * 根据条件分页查询位置列表
     *
     * @param queryRequest 查询请求
     * @return 分页结果
     */
    PageResult<LocationDTO> getLocationList(LocationQueryRequest queryRequest);
    
    /**
     * 根据类型查询位置列表
     *
     * @param type     位置类型
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 分页结果
     */
    PageResult<LocationDTO> getLocationListByType(Integer type, Integer pageNum, Integer pageSize);
    
    /**
     * 根据城市查询位置列表
     *
     * @param city     城市
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 分页结果
     */
    PageResult<LocationDTO> getLocationListByCity(String city, Integer pageNum, Integer pageSize);
    
    /**
     * 创建位置
     *
     * @param location 位置实体
     * @return 位置ID
     */
    Long createLocation(Location location);
    
    /**
     * 更新位置
     *
     * @param location 位置实体
     * @return 是否成功
     */
    boolean updateLocation(Location location);
    
    /**
     * 删除位置
     *
     * @param id 位置ID
     * @return 是否成功
     */
    boolean deleteLocation(Long id);
} 
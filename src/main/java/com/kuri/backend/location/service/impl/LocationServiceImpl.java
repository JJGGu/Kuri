package com.kuri.backend.location.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kuri.backend.common.exception.BusinessException;
import com.kuri.backend.common.model.PageResult;
import com.kuri.backend.common.model.ResultCode;
import com.kuri.backend.location.dto.LocationDTO;
import com.kuri.backend.location.dto.LocationQueryRequest;
import com.kuri.backend.location.mapper.LocationMapper;
import com.kuri.backend.location.model.Location;
import com.kuri.backend.location.service.LocationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 位置服务实现类
 */
@Service
public class LocationServiceImpl implements LocationService {
    
    @Autowired
    private LocationMapper locationMapper;
    
    @Override
    public LocationDTO getLocationById(Long id) {
        Location location = locationMapper.selectById(id);
        if (location == null) {
            throw new BusinessException(ResultCode.NOT_FOUND, "位置不存在");
        }
        return convertToDTO(location);
    }
    
    @Override
    public PageResult<LocationDTO> getLocationList(LocationQueryRequest queryRequest) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", queryRequest.getName());
        params.put("type", queryRequest.getType());
        params.put("city", queryRequest.getCity());
        params.put("province", queryRequest.getProvince());
        params.put("country", queryRequest.getCountry());
        params.put("minRating", queryRequest.getMinRating());
        
        Page<Location> page = PageHelper.startPage(queryRequest.getPageNum(), queryRequest.getPageSize());
        List<Location> locations = locationMapper.selectByCondition(params);
        
        List<LocationDTO> locationDTOs = new ArrayList<>();
        for (Location location : locations) {
            locationDTOs.add(convertToDTO(location));
        }
        
        return PageResult.of(locationDTOs, page.getTotal(), queryRequest.getPageNum(), queryRequest.getPageSize());
    }
    
    @Override
    public PageResult<LocationDTO> getLocationListByType(Integer type, Integer pageNum, Integer pageSize) {
        Page<Location> page = PageHelper.startPage(pageNum, pageSize);
        List<Location> locations = locationMapper.selectByType(type);
        
        List<LocationDTO> locationDTOs = new ArrayList<>();
        for (Location location : locations) {
            locationDTOs.add(convertToDTO(location));
        }
        
        return PageResult.of(locationDTOs, page.getTotal(), pageNum, pageSize);
    }
    
    @Override
    public PageResult<LocationDTO> getLocationListByCity(String city, Integer pageNum, Integer pageSize) {
        Page<Location> page = PageHelper.startPage(pageNum, pageSize);
        List<Location> locations = locationMapper.selectByCity(city);
        
        List<LocationDTO> locationDTOs = new ArrayList<>();
        for (Location location : locations) {
            locationDTOs.add(convertToDTO(location));
        }
        
        return PageResult.of(locationDTOs, page.getTotal(), pageNum, pageSize);
    }
    
    @Override
    @Transactional
    public Long createLocation(Location location) {
        // 设置默认值
        location.setStatus(1);
        location.setDeleted(0);
        if (location.getRating() == null) {
            location.setRating(0.0);
        }
        if (location.getRatingCount() == null) {
            location.setRatingCount(0);
        }
        
        LocalDateTime now = LocalDateTime.now();
        location.setCreateTime(now);
        location.setUpdateTime(now);
        
        locationMapper.insert(location);
        return location.getId();
    }
    
    @Override
    @Transactional
    public boolean updateLocation(Location location) {
        Location existingLocation = locationMapper.selectById(location.getId());
        if (existingLocation == null) {
            throw new BusinessException(ResultCode.NOT_FOUND, "位置不存在");
        }
        
        location.setUpdateTime(LocalDateTime.now());
        return locationMapper.update(location) > 0;
    }
    
    @Override
    @Transactional
    public boolean deleteLocation(Long id) {
        Location location = locationMapper.selectById(id);
        if (location == null) {
            throw new BusinessException(ResultCode.NOT_FOUND, "位置不存在");
        }
        return locationMapper.deleteById(id) > 0;
    }
    
    /**
     * 将位置实体转换为DTO
     *
     * @param location 位置实体
     * @return 位置DTO
     */
    private LocationDTO convertToDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        BeanUtils.copyProperties(location, locationDTO);
        
        // 设置位置类型名称
        if (location.getType() != null) {
            switch (location.getType()) {
                case 1 -> locationDTO.setTypeName("景点");
                case 2 -> locationDTO.setTypeName("餐厅");
                case 3 -> locationDTO.setTypeName("酒店");
                case 4 -> locationDTO.setTypeName("购物");
                case 5 -> locationDTO.setTypeName("娱乐");
                default -> locationDTO.setTypeName("未知");
            }
        }
        
        return locationDTO;
    }
} 
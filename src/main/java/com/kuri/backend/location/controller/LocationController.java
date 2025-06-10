package com.kuri.backend.location.controller;

import com.kuri.backend.common.model.PageResult;
import com.kuri.backend.common.model.Result;
import com.kuri.backend.location.dto.LocationDTO;
import com.kuri.backend.location.dto.LocationQueryRequest;
import com.kuri.backend.location.model.Location;
import com.kuri.backend.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 位置控制器
 */
@RestController
@RequestMapping("/api/locations")
public class LocationController {
    
    @Autowired
    private LocationService locationService;
    
    /**
     * 获取位置信息
     */
    @GetMapping("/{id}")
    public Result<LocationDTO> getLocationById(@PathVariable Long id) {
        return Result.success(locationService.getLocationById(id));
    }
    
    /**
     * 条件查询位置列表
     */
    @GetMapping
    public Result<PageResult<LocationDTO>> getLocationList(LocationQueryRequest queryRequest) {
        return Result.success(locationService.getLocationList(queryRequest));
    }
    
    /**
     * 根据类型查询位置列表
     */
    @GetMapping("/type/{type}")
    public Result<PageResult<LocationDTO>> getLocationListByType(
            @PathVariable Integer type,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(locationService.getLocationListByType(type, pageNum, pageSize));
    }
    
    /**
     * 根据城市查询位置列表
     */
    @GetMapping("/city/{city}")
    public Result<PageResult<LocationDTO>> getLocationListByCity(
            @PathVariable String city,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(locationService.getLocationListByCity(city, pageNum, pageSize));
    }
    
    /**
     * 创建位置
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Result<Long> createLocation(@Valid @RequestBody Location location) {
        return Result.success(locationService.createLocation(location));
    }
    
    /**
     * 更新位置
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Result<Boolean> updateLocation(@PathVariable Long id, @Valid @RequestBody Location location) {
        location.setId(id);
        return Result.success(locationService.updateLocation(location));
    }
    
    /**
     * 删除位置
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Result<Boolean> deleteLocation(@PathVariable Long id) {
        return Result.success(locationService.deleteLocation(id));
    }
} 
package com.kuri.backend.trip.mapper;

import com.kuri.backend.trip.model.TransportationDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 交通详情Mapper接口
 */
@Mapper
public interface TransportationDetailMapper {
    
    /**
     * 根据ID查询交通详情
     *
     * @param id 交通详情ID
     * @return 交通详情对象
     */
    @Select("SELECT * FROM transportation_details WHERE id = #{id}")
    TransportationDetail selectById(@Param("id") Long id);
    
    /**
     * 根据起点和终点查询交通详情
     *
     * @param fromPoiId 起点POI ID
     * @param toPoiId 终点POI ID
     * @return 交通详情列表
     */
    @Select("SELECT * FROM transportation_details WHERE from_poi_id = #{fromPoiId} AND to_poi_id = #{toPoiId}")
    List<TransportationDetail> selectByFromToPoiId(@Param("fromPoiId") String fromPoiId, @Param("toPoiId") String toPoiId);
    
    /**
     * 根据交通方式查询交通详情
     *
     * @param mode 交通方式
     * @return 交通详情列表
     */
    @Select("SELECT * FROM transportation_details WHERE mode = #{mode}")
    List<TransportationDetail> selectByMode(@Param("mode") String mode);
    
    /**
     * 根据服务提供商查询交通详情
     *
     * @param provider 服务提供商
     * @return 交通详情列表
     */
    @Select("SELECT * FROM transportation_details WHERE provider = #{provider}")
    List<TransportationDetail> selectByProvider(@Param("provider") String provider);
    
    /**
     * 根据高德路线ID查询交通详情
     *
     * @param amapRouteId 高德路线ID
     * @return 交通详情对象
     */
    @Select("SELECT * FROM transportation_details WHERE amap_route_id = #{amapRouteId}")
    TransportationDetail selectByAmapRouteId(@Param("amapRouteId") String amapRouteId);
    
    /**
     * 插入交通详情
     *
     * @param transportationDetail 交通详情对象
     * @return 影响行数
     */
    @Insert("INSERT INTO transportation_details (mode, provider, from_name, from_latitude, from_longitude, from_poi_id, " +
            "to_name, to_latitude, to_longitude, to_poi_id, distance, duration, route_description, route_steps, " +
            "base_cost, estimated_cost, cost_breakdown, departure_time, arrival_time, schedule_info, " +
            "booking_required, booking_url, booking_platforms, navigation_url, amap_route_id, tips, restrictions, data_source, last_verified_at) " +
            "VALUES (#{mode}, #{provider}, #{fromName}, #{fromLatitude}, #{fromLongitude}, #{fromPoiId}, " +
            "#{toName}, #{toLatitude}, #{toLongitude}, #{toPoiId}, #{distance}, #{duration}, #{routeDescription}, #{routeSteps}, " +
            "#{baseCost}, #{estimatedCost}, #{costBreakdown}, #{departureTime}, #{arrivalTime}, #{scheduleInfo}, " +
            "#{bookingRequired}, #{bookingUrl}, #{bookingPlatforms}, #{navigationUrl}, #{amapRouteId}, #{tips}, #{restrictions}, #{dataSource}, #{lastVerifiedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(TransportationDetail transportationDetail);
    
    /**
     * 更新交通详情
     *
     * @param transportationDetail 交通详情对象
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE transportation_details " +
            "<set>" +
            "  <if test='mode != null'>mode = #{mode},</if>" +
            "  <if test='provider != null'>provider = #{provider},</if>" +
            "  <if test='fromName != null'>from_name = #{fromName},</if>" +
            "  <if test='fromLatitude != null'>from_latitude = #{fromLatitude},</if>" +
            "  <if test='fromLongitude != null'>from_longitude = #{fromLongitude},</if>" +
            "  <if test='fromPoiId != null'>from_poi_id = #{fromPoiId},</if>" +
            "  <if test='toName != null'>to_name = #{toName},</if>" +
            "  <if test='toLatitude != null'>to_latitude = #{toLatitude},</if>" +
            "  <if test='toLongitude != null'>to_longitude = #{toLongitude},</if>" +
            "  <if test='toPoiId != null'>to_poi_id = #{toPoiId},</if>" +
            "  <if test='distance != null'>distance = #{distance},</if>" +
            "  <if test='duration != null'>duration = #{duration},</if>" +
            "  <if test='routeDescription != null'>route_description = #{routeDescription},</if>" +
            "  <if test='routeSteps != null'>route_steps = #{routeSteps},</if>" +
            "  <if test='baseCost != null'>base_cost = #{baseCost},</if>" +
            "  <if test='estimatedCost != null'>estimated_cost = #{estimatedCost},</if>" +
            "  <if test='costBreakdown != null'>cost_breakdown = #{costBreakdown},</if>" +
            "  <if test='departureTime != null'>departure_time = #{departureTime},</if>" +
            "  <if test='arrivalTime != null'>arrival_time = #{arrivalTime},</if>" +
            "  <if test='scheduleInfo != null'>schedule_info = #{scheduleInfo},</if>" +
            "  <if test='bookingRequired != null'>booking_required = #{bookingRequired},</if>" +
            "  <if test='bookingUrl != null'>booking_url = #{bookingUrl},</if>" +
            "  <if test='bookingPlatforms != null'>booking_platforms = #{bookingPlatforms},</if>" +
            "  <if test='navigationUrl != null'>navigation_url = #{navigationUrl},</if>" +
            "  <if test='amapRouteId != null'>amap_route_id = #{amapRouteId},</if>" +
            "  <if test='tips != null'>tips = #{tips},</if>" +
            "  <if test='restrictions != null'>restrictions = #{restrictions},</if>" +
            "  <if test='dataSource != null'>data_source = #{dataSource},</if>" +
            "  <if test='lastVerifiedAt != null'>last_verified_at = #{lastVerifiedAt},</if>" +
            "  updated_at = NOW() " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int update(TransportationDetail transportationDetail);
    
    /**
     * 删除交通详情
     *
     * @param id 交通详情ID
     * @return 影响行数
     */
    @Delete("DELETE FROM transportation_details WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
} 
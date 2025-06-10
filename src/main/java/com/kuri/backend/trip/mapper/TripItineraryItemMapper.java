package com.kuri.backend.trip.mapper;

import com.kuri.backend.trip.model.TripItineraryItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 行程项目Mapper接口
 */
@Mapper
public interface TripItineraryItemMapper {
    
    /**
     * 根据ID查询行程项目
     *
     * @param id 行程项目ID
     * @return 行程项目对象
     */
    @Select("SELECT * FROM trip_itinerary_items WHERE id = #{id}")
    TripItineraryItem selectById(@Param("id") Long id);
    
    /**
     * 根据行程ID查询所有项目
     *
     * @param tripId 行程ID
     * @return 项目列表
     */
    @Select("SELECT * FROM trip_itinerary_items WHERE trip_id = #{tripId} ORDER BY day_index, item_order")
    List<TripItineraryItem> selectByTripId(@Param("tripId") Long tripId);
    
    /**
     * 根据日程ID查询项目
     *
     * @param dayId 日程ID
     * @return 项目列表
     */
    @Select("SELECT * FROM trip_itinerary_items WHERE day_id = #{dayId} ORDER BY item_order")
    List<TripItineraryItem> selectByDayId(@Param("dayId") Long dayId);
    
    /**
     * 根据行程ID和天数查询项目
     *
     * @param tripId 行程ID
     * @param dayIndex 天数索引
     * @return 项目列表
     */
    @Select("SELECT * FROM trip_itinerary_items WHERE trip_id = #{tripId} AND day_index = #{dayIndex} ORDER BY item_order")
    List<TripItineraryItem> selectByTripIdAndDayIndex(@Param("tripId") Long tripId, @Param("dayIndex") Integer dayIndex);
    
    /**
     * 根据类型查询项目
     *
     * @param tripId 行程ID
     * @param type 项目类型
     * @return 项目列表
     */
    @Select("SELECT * FROM trip_itinerary_items WHERE trip_id = #{tripId} AND type = #{type} ORDER BY day_index, item_order")
    List<TripItineraryItem> selectByTripIdAndType(@Param("tripId") Long tripId, @Param("type") String type);
    
    /**
     * 插入行程项目
     *
     * @param item 行程项目对象
     * @return 影响行数
     */
    @Insert("INSERT INTO trip_itinerary_items (trip_id, day_id, day_index, item_order, type, name, description, " +
            "start_time, end_time, duration, address, city, latitude, longitude, amap_poi_id, " +
            "estimated_cost, detail_id, notes, status) " +
            "VALUES (#{tripId}, #{dayId}, #{dayIndex}, #{itemOrder}, #{type}, #{name}, #{description}, " +
            "#{startTime}, #{endTime}, #{duration}, #{address}, #{city}, #{latitude}, #{longitude}, #{amapPoiId}, " +
            "#{estimatedCost}, #{detailId}, #{notes}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(TripItineraryItem item);
    
    /**
     * 批量插入行程项目
     *
     * @param items 行程项目对象列表
     * @return 影响行数
     */
    @Insert("<script>" +
            "INSERT INTO trip_itinerary_items (trip_id, day_id, day_index, item_order, type, name, description, " +
            "start_time, end_time, duration, address, city, latitude, longitude, amap_poi_id, " +
            "estimated_cost, detail_id, notes, status) VALUES " +
            "<foreach collection='list' item='item' separator=','>" +
            "(#{item.tripId}, #{item.dayId}, #{item.dayIndex}, #{item.itemOrder}, #{item.type}, #{item.name}, #{item.description}, " +
            "#{item.startTime}, #{item.endTime}, #{item.duration}, #{item.address}, #{item.city}, " +
            "#{item.latitude}, #{item.longitude}, #{item.amapPoiId}, #{item.estimatedCost}, " +
            "#{item.detailId}, #{item.notes}, #{item.status})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("list") List<TripItineraryItem> items);
    
    /**
     * 更新行程项目
     *
     * @param item 行程项目对象
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE trip_itinerary_items " +
            "<set>" +
            "  <if test='itemOrder != null'>item_order = #{itemOrder},</if>" +
            "  <if test='name != null'>name = #{name},</if>" +
            "  <if test='description != null'>description = #{description},</if>" +
            "  <if test='startTime != null'>start_time = #{startTime},</if>" +
            "  <if test='endTime != null'>end_time = #{endTime},</if>" +
            "  <if test='duration != null'>duration = #{duration},</if>" +
            "  <if test='address != null'>address = #{address},</if>" +
            "  <if test='city != null'>city = #{city},</if>" +
            "  <if test='latitude != null'>latitude = #{latitude},</if>" +
            "  <if test='longitude != null'>longitude = #{longitude},</if>" +
            "  <if test='amapPoiId != null'>amap_poi_id = #{amapPoiId},</if>" +
            "  <if test='estimatedCost != null'>estimated_cost = #{estimatedCost},</if>" +
            "  <if test='detailId != null'>detail_id = #{detailId},</if>" +
            "  <if test='notes != null'>notes = #{notes},</if>" +
            "  <if test='status != null'>status = #{status},</if>" +
            "  updated_at = NOW() " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int update(TripItineraryItem item);
    
    /**
     * 删除行程项目
     *
     * @param id 行程项目ID
     * @return 影响行数
     */
    @Delete("DELETE FROM trip_itinerary_items WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
    
    /**
     * 根据行程ID删除所有项目
     *
     * @param tripId 行程ID
     * @return 影响行数
     */
    @Delete("DELETE FROM trip_itinerary_items WHERE trip_id = #{tripId}")
    int deleteByTripId(@Param("tripId") Long tripId);
    
    /**
     * 根据日程ID删除项目
     *
     * @param dayId 日程ID
     * @return 影响行数
     */
    @Delete("DELETE FROM trip_itinerary_items WHERE day_id = #{dayId}")
    int deleteByDayId(@Param("dayId") Long dayId);
    
    /**
     * 更新项目状态
     *
     * @param id 项目ID
     * @param status 状态
     * @return 影响行数
     */
    @Update("UPDATE trip_itinerary_items SET status = #{status}, updated_at = NOW() WHERE id = #{id}")
    int updateStatus(@Param("id") Long id, @Param("status") String status);
} 
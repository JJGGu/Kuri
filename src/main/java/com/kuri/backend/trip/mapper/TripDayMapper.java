package com.kuri.backend.trip.mapper;

import com.kuri.backend.trip.model.TripDay;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * 行程日程Mapper接口
 */
@Mapper
public interface TripDayMapper {
    
    /**
     * 根据ID查询行程日程
     *
     * @param id 行程日程ID
     * @return 行程日程对象
     */
    @Select("SELECT * FROM trip_days WHERE id = #{id}")
    TripDay selectById(@Param("id") Long id);
    
    /**
     * 根据行程ID查询所有日程
     *
     * @param tripId 行程ID
     * @return 日程列表
     */
    @Select("SELECT * FROM trip_days WHERE trip_id = #{tripId} ORDER BY day_index")
    List<TripDay> selectByTripId(@Param("tripId") Long tripId);
    
    /**
     * 根据行程ID和日期查询日程
     *
     * @param tripId 行程ID
     * @param date 日期
     * @return 日程对象
     */
    @Select("SELECT * FROM trip_days WHERE trip_id = #{tripId} AND date = #{date}")
    TripDay selectByTripIdAndDate(@Param("tripId") Long tripId, @Param("date") Date date);
    
    /**
     * 根据行程ID和天数索引查询日程
     *
     * @param tripId 行程ID
     * @param dayIndex 天数索引
     * @return 日程对象
     */
    @Select("SELECT * FROM trip_days WHERE trip_id = #{tripId} AND day_index = #{dayIndex}")
    TripDay selectByTripIdAndDayIndex(@Param("tripId") Long tripId, @Param("dayIndex") Integer dayIndex);
    
    /**
     * 插入行程日程
     *
     * @param tripDay 行程日程对象
     * @return 影响行数
     */
    @Insert("INSERT INTO trip_days (trip_id, day_index, date, title, summary, city, theme, " +
            "weather_condition, weather_temperature, weather_humidity, weather_wind, " +
            "start_location_name, start_location_time, start_location_poi_id, " +
            "end_location_name, end_location_time, end_location_poi_id, " +
            "estimated_cost, is_generated) " +
            "VALUES (#{tripId}, #{dayIndex}, #{date}, #{title}, #{summary}, #{city}, #{theme}, " +
            "#{weatherCondition}, #{weatherTemperature}, #{weatherHumidity}, #{weatherWind}, " +
            "#{startLocationName}, #{startLocationTime}, #{startLocationPoiId}, " +
            "#{endLocationName}, #{endLocationTime}, #{endLocationPoiId}, " +
            "#{estimatedCost}, #{isGenerated})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(TripDay tripDay);
    
    /**
     * 更新行程日程
     *
     * @param tripDay 行程日程对象
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE trip_days " +
            "<set>" +
            "  <if test='title != null'>title = #{title},</if>" +
            "  <if test='summary != null'>summary = #{summary},</if>" +
            "  <if test='city != null'>city = #{city},</if>" +
            "  <if test='theme != null'>theme = #{theme},</if>" +
            "  <if test='weatherCondition != null'>weather_condition = #{weatherCondition},</if>" +
            "  <if test='weatherTemperature != null'>weather_temperature = #{weatherTemperature},</if>" +
            "  <if test='weatherHumidity != null'>weather_humidity = #{weatherHumidity},</if>" +
            "  <if test='weatherWind != null'>weather_wind = #{weatherWind},</if>" +
            "  <if test='startLocationName != null'>start_location_name = #{startLocationName},</if>" +
            "  <if test='startLocationTime != null'>start_location_time = #{startLocationTime},</if>" +
            "  <if test='startLocationPoiId != null'>start_location_poi_id = #{startLocationPoiId},</if>" +
            "  <if test='endLocationName != null'>end_location_name = #{endLocationName},</if>" +
            "  <if test='endLocationTime != null'>end_location_time = #{endLocationTime},</if>" +
            "  <if test='endLocationPoiId != null'>end_location_poi_id = #{endLocationPoiId},</if>" +
            "  <if test='estimatedCost != null'>estimated_cost = #{estimatedCost},</if>" +
            "  <if test='isGenerated != null'>is_generated = #{isGenerated},</if>" +
            "  updated_at = NOW() " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int update(TripDay tripDay);
    
    /**
     * 删除行程日程
     *
     * @param id 行程日程ID
     * @return 影响行数
     */
    @Delete("DELETE FROM trip_days WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
    
    /**
     * 根据行程ID删除所有日程
     *
     * @param tripId 行程ID
     * @return 影响行数
     */
    @Delete("DELETE FROM trip_days WHERE trip_id = #{tripId}")
    int deleteByTripId(@Param("tripId") Long tripId);
    
    /**
     * 更新日程生成状态
     *
     * @param id 日程ID
     * @param isGenerated 是否已生成
     * @return 影响行数
     */
    @Update("UPDATE trip_days SET is_generated = #{isGenerated}, updated_at = NOW() WHERE id = #{id}")
    int updateGenerationStatus(@Param("id") Long id, @Param("isGenerated") Boolean isGenerated);
} 
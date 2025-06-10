package com.kuri.backend.location.mapper;

import com.kuri.backend.location.model.AttractionDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 景点详情Mapper接口
 */
@Mapper
public interface AttractionDetailMapper {
    
    /**
     * 根据ID查询景点详情
     *
     * @param id 景点详情ID
     * @return 景点详情对象
     */
    @Select("SELECT * FROM attraction_details WHERE id = #{id}")
    AttractionDetail selectById(@Param("id") Long id);
    
    /**
     * 根据高德POI ID查询景点详情
     *
     * @param amapPoiId 高德POI ID
     * @return 景点详情对象
     */
    @Select("SELECT * FROM attraction_details WHERE amap_poi_id = #{amapPoiId}")
    AttractionDetail selectByAmapPoiId(@Param("amapPoiId") String amapPoiId);
    
    /**
     * 根据名称查询景点详情
     *
     * @param name 景点名称
     * @return 景点详情列表
     */
    @Select("SELECT * FROM attraction_details WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<AttractionDetail> selectByName(@Param("name") String name);
    
    /**
     * 根据城市查询景点详情
     *
     * @param city 城市
     * @return 景点详情列表
     */
    @Select("SELECT * FROM attraction_details WHERE city = #{city}")
    List<AttractionDetail> selectByCity(@Param("city") String city);
    
    /**
     * 根据分类查询景点详情
     *
     * @param category 分类
     * @return 景点详情列表
     */
    @Select("SELECT * FROM attraction_details WHERE category = #{category}")
    List<AttractionDetail> selectByCategory(@Param("category") String category);
    
    /**
     * 根据等级查询景点详情
     *
     * @param level 等级
     * @return 景点详情列表
     */
    @Select("SELECT * FROM attraction_details WHERE level = #{level}")
    List<AttractionDetail> selectByLevel(@Param("level") String level);
    
    /**
     * 插入景点详情
     *
     * @param attractionDetail 景点详情对象
     * @return 影响行数
     */
    @Insert("INSERT INTO attraction_details (name, category, level, description, highlights, history, best_visit_time, suggested_duration, " +
            "address, city, latitude, longitude, amap_poi_id, ticket_price, ticket_info, booking_url, business_hours, seasonal_hours, " +
            "contact_phone, official_website, rating, review_count, cover_image, images, videos, facilities, services, " +
            "transportation_guide, parking_info, tour_routes, tips, restrictions, tags, data_source, last_verified_at) " +
            "VALUES (#{name}, #{category}, #{level}, #{description}, #{highlights}, #{history}, #{bestVisitTime}, #{suggestedDuration}, " +
            "#{address}, #{city}, #{latitude}, #{longitude}, #{amapPoiId}, #{ticketPrice}, #{ticketInfo}, #{bookingUrl}, #{businessHours}, #{seasonalHours}, " +
            "#{contactPhone}, #{officialWebsite}, #{rating}, #{reviewCount}, #{coverImage}, #{images}, #{videos}, #{facilities}, #{services}, " +
            "#{transportationGuide}, #{parkingInfo}, #{tourRoutes}, #{tips}, #{restrictions}, #{tags}, #{dataSource}, #{lastVerifiedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(AttractionDetail attractionDetail);
    
    /**
     * 更新景点详情
     *
     * @param attractionDetail 景点详情对象
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE attraction_details " +
            "<set>" +
            "  <if test='name != null'>name = #{name},</if>" +
            "  <if test='category != null'>category = #{category},</if>" +
            "  <if test='level != null'>level = #{level},</if>" +
            "  <if test='description != null'>description = #{description},</if>" +
            "  <if test='highlights != null'>highlights = #{highlights},</if>" +
            "  <if test='history != null'>history = #{history},</if>" +
            "  <if test='bestVisitTime != null'>best_visit_time = #{bestVisitTime},</if>" +
            "  <if test='suggestedDuration != null'>suggested_duration = #{suggestedDuration},</if>" +
            "  <if test='address != null'>address = #{address},</if>" +
            "  <if test='city != null'>city = #{city},</if>" +
            "  <if test='latitude != null'>latitude = #{latitude},</if>" +
            "  <if test='longitude != null'>longitude = #{longitude},</if>" +
            "  <if test='amapPoiId != null'>amap_poi_id = #{amapPoiId},</if>" +
            "  <if test='ticketPrice != null'>ticket_price = #{ticketPrice},</if>" +
            "  <if test='ticketInfo != null'>ticket_info = #{ticketInfo},</if>" +
            "  <if test='bookingUrl != null'>booking_url = #{bookingUrl},</if>" +
            "  <if test='businessHours != null'>business_hours = #{businessHours},</if>" +
            "  <if test='seasonalHours != null'>seasonal_hours = #{seasonalHours},</if>" +
            "  <if test='contactPhone != null'>contact_phone = #{contactPhone},</if>" +
            "  <if test='officialWebsite != null'>official_website = #{officialWebsite},</if>" +
            "  <if test='rating != null'>rating = #{rating},</if>" +
            "  <if test='reviewCount != null'>review_count = #{reviewCount},</if>" +
            "  <if test='coverImage != null'>cover_image = #{coverImage},</if>" +
            "  <if test='images != null'>images = #{images},</if>" +
            "  <if test='videos != null'>videos = #{videos},</if>" +
            "  <if test='facilities != null'>facilities = #{facilities},</if>" +
            "  <if test='services != null'>services = #{services},</if>" +
            "  <if test='transportationGuide != null'>transportation_guide = #{transportationGuide},</if>" +
            "  <if test='parkingInfo != null'>parking_info = #{parkingInfo},</if>" +
            "  <if test='tourRoutes != null'>tour_routes = #{tourRoutes},</if>" +
            "  <if test='tips != null'>tips = #{tips},</if>" +
            "  <if test='restrictions != null'>restrictions = #{restrictions},</if>" +
            "  <if test='tags != null'>tags = #{tags},</if>" +
            "  <if test='dataSource != null'>data_source = #{dataSource},</if>" +
            "  <if test='lastVerifiedAt != null'>last_verified_at = #{lastVerifiedAt},</if>" +
            "  updated_at = NOW() " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int update(AttractionDetail attractionDetail);
    
    /**
     * 删除景点详情
     *
     * @param id 景点详情ID
     * @return 影响行数
     */
    @Delete("DELETE FROM attraction_details WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
} 
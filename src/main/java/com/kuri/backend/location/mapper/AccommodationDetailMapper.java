package com.kuri.backend.location.mapper;

import com.kuri.backend.location.model.AccommodationDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 酒店详情Mapper接口
 */
@Mapper
public interface AccommodationDetailMapper {
    
    /**
     * 根据ID查询酒店详情
     *
     * @param id 酒店详情ID
     * @return 酒店详情对象
     */
    @Select("SELECT * FROM accommodation_details WHERE id = #{id}")
    AccommodationDetail selectById(@Param("id") Long id);
    
    /**
     * 根据高德POI ID查询酒店详情
     *
     * @param amapPoiId 高德POI ID
     * @return 酒店详情对象
     */
    @Select("SELECT * FROM accommodation_details WHERE amap_poi_id = #{amapPoiId}")
    AccommodationDetail selectByAmapPoiId(@Param("amapPoiId") String amapPoiId);
    
    /**
     * 根据名称查询酒店详情
     *
     * @param name 酒店名称
     * @return 酒店详情列表
     */
    @Select("SELECT * FROM accommodation_details WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<AccommodationDetail> selectByName(@Param("name") String name);
    
    /**
     * 根据城市查询酒店详情
     *
     * @param city 城市
     * @return 酒店详情列表
     */
    @Select("SELECT * FROM accommodation_details WHERE city = #{city}")
    List<AccommodationDetail> selectByCity(@Param("city") String city);
    
    /**
     * 根据品牌查询酒店详情
     *
     * @param brand 品牌
     * @return 酒店详情列表
     */
    @Select("SELECT * FROM accommodation_details WHERE brand = #{brand}")
    List<AccommodationDetail> selectByBrand(@Param("brand") String brand);
    
    /**
     * 根据分类查询酒店详情
     *
     * @param category 分类
     * @return 酒店详情列表
     */
    @Select("SELECT * FROM accommodation_details WHERE category = #{category}")
    List<AccommodationDetail> selectByCategory(@Param("category") String category);
    
    /**
     * 根据星级查询酒店详情
     *
     * @param starLevel 星级
     * @return 酒店详情列表
     */
    @Select("SELECT * FROM accommodation_details WHERE star_level = #{starLevel}")
    List<AccommodationDetail> selectByStarLevel(@Param("starLevel") String starLevel);
    
    /**
     * 插入酒店详情
     *
     * @param accommodationDetail 酒店详情对象
     * @return 影响行数
     */
    @Insert("INSERT INTO accommodation_details (amap_poi_id, name, brand, category, star_level, address, city, latitude, longitude, " +
            "description, features, price_range, average_price, booking_platforms, check_in_time, check_out_time, " +
            "contact_phone, official_website, rating, review_count, cleanliness_score, service_score, location_score, " +
            "cover_image, images, room_images, facilities, room_facilities, services, room_types, policies, " +
            "transportation_info, nearby_attractions, tags, data_source, last_verified_at) " +
            "VALUES (#{amapPoiId}, #{name}, #{brand}, #{category}, #{starLevel}, #{address}, #{city}, #{latitude}, #{longitude}, " +
            "#{description}, #{features}, #{priceRange}, #{averagePrice}, #{bookingPlatforms}, #{checkInTime}, #{checkOutTime}, " +
            "#{contactPhone}, #{officialWebsite}, #{rating}, #{reviewCount}, #{cleanlinessScore}, #{serviceScore}, #{locationScore}, " +
            "#{coverImage}, #{images}, #{roomImages}, #{facilities}, #{roomFacilities}, #{services}, #{roomTypes}, #{policies}, " +
            "#{transportationInfo}, #{nearbyAttractions}, #{tags}, #{dataSource}, #{lastVerifiedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(AccommodationDetail accommodationDetail);
    
    /**
     * 更新酒店详情
     *
     * @param accommodationDetail 酒店详情对象
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE accommodation_details " +
            "<set>" +
            "  <if test='amapPoiId != null'>amap_poi_id = #{amapPoiId},</if>" +
            "  <if test='name != null'>name = #{name},</if>" +
            "  <if test='brand != null'>brand = #{brand},</if>" +
            "  <if test='category != null'>category = #{category},</if>" +
            "  <if test='starLevel != null'>star_level = #{starLevel},</if>" +
            "  <if test='address != null'>address = #{address},</if>" +
            "  <if test='city != null'>city = #{city},</if>" +
            "  <if test='latitude != null'>latitude = #{latitude},</if>" +
            "  <if test='longitude != null'>longitude = #{longitude},</if>" +
            "  <if test='description != null'>description = #{description},</if>" +
            "  <if test='features != null'>features = #{features},</if>" +
            "  <if test='priceRange != null'>price_range = #{priceRange},</if>" +
            "  <if test='averagePrice != null'>average_price = #{averagePrice},</if>" +
            "  <if test='bookingPlatforms != null'>booking_platforms = #{bookingPlatforms},</if>" +
            "  <if test='checkInTime != null'>check_in_time = #{checkInTime},</if>" +
            "  <if test='checkOutTime != null'>check_out_time = #{checkOutTime},</if>" +
            "  <if test='contactPhone != null'>contact_phone = #{contactPhone},</if>" +
            "  <if test='officialWebsite != null'>official_website = #{officialWebsite},</if>" +
            "  <if test='rating != null'>rating = #{rating},</if>" +
            "  <if test='reviewCount != null'>review_count = #{reviewCount},</if>" +
            "  <if test='cleanlinessScore != null'>cleanliness_score = #{cleanlinessScore},</if>" +
            "  <if test='serviceScore != null'>service_score = #{serviceScore},</if>" +
            "  <if test='locationScore != null'>location_score = #{locationScore},</if>" +
            "  <if test='coverImage != null'>cover_image = #{coverImage},</if>" +
            "  <if test='images != null'>images = #{images},</if>" +
            "  <if test='roomImages != null'>room_images = #{roomImages},</if>" +
            "  <if test='facilities != null'>facilities = #{facilities},</if>" +
            "  <if test='roomFacilities != null'>room_facilities = #{roomFacilities},</if>" +
            "  <if test='services != null'>services = #{services},</if>" +
            "  <if test='roomTypes != null'>room_types = #{roomTypes},</if>" +
            "  <if test='policies != null'>policies = #{policies},</if>" +
            "  <if test='transportationInfo != null'>transportation_info = #{transportationInfo},</if>" +
            "  <if test='nearbyAttractions != null'>nearby_attractions = #{nearbyAttractions},</if>" +
            "  <if test='tags != null'>tags = #{tags},</if>" +
            "  <if test='dataSource != null'>data_source = #{dataSource},</if>" +
            "  <if test='lastVerifiedAt != null'>last_verified_at = #{lastVerifiedAt},</if>" +
            "  updated_at = NOW() " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int update(AccommodationDetail accommodationDetail);
    
    /**
     * 删除酒店详情
     *
     * @param id 酒店详情ID
     * @return 影响行数
     */
    @Delete("DELETE FROM accommodation_details WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
} 
package com.kuri.backend.location.mapper;

import com.kuri.backend.location.model.FoodDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 美食详情Mapper接口
 */
@Mapper
public interface FoodDetailMapper {
    
    /**
     * 根据ID查询美食详情
     *
     * @param id 美食详情ID
     * @return 美食详情对象
     */
    @Select("SELECT * FROM food_details WHERE id = #{id}")
    FoodDetail selectById(@Param("id") Long id);
    
    /**
     * 根据高德POI ID查询美食详情
     *
     * @param amapPoiId 高德POI ID
     * @return 美食详情对象
     */
    @Select("SELECT * FROM food_details WHERE amap_poi_id = #{amapPoiId}")
    FoodDetail selectByAmapPoiId(@Param("amapPoiId") String amapPoiId);
    
    /**
     * 根据名称查询美食详情
     *
     * @param name 餐厅名称
     * @return 美食详情列表
     */
    @Select("SELECT * FROM food_details WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<FoodDetail> selectByName(@Param("name") String name);
    
    /**
     * 根据城市查询美食详情
     *
     * @param city 城市
     * @return 美食详情列表
     */
    @Select("SELECT * FROM food_details WHERE city = #{city}")
    List<FoodDetail> selectByCity(@Param("city") String city);
    
    /**
     * 根据菜系查询美食详情
     *
     * @param cuisineType 菜系
     * @return 美食详情列表
     */
    @Select("SELECT * FROM food_details WHERE cuisine_type = #{cuisineType}")
    List<FoodDetail> selectByCuisineType(@Param("cuisineType") String cuisineType);
    
    /**
     * 根据分类查询美食详情
     *
     * @param category 分类
     * @return 美食详情列表
     */
    @Select("SELECT * FROM food_details WHERE category = #{category}")
    List<FoodDetail> selectByCategory(@Param("category") String category);
    
    /**
     * 根据价格等级查询美食详情
     *
     * @param priceLevel 价格等级
     * @return 美食详情列表
     */
    @Select("SELECT * FROM food_details WHERE price_level = #{priceLevel}")
    List<FoodDetail> selectByPriceLevel(@Param("priceLevel") String priceLevel);
    
    /**
     * 插入美食详情
     *
     * @param foodDetail 美食详情对象
     * @return 影响行数
     */
    @Insert("INSERT INTO food_details (amap_poi_id, name, cuisine_type, category, address, city, latitude, longitude, " +
            "description, specialties, atmosphere, price_level, average_cost, business_hours, break_time, " +
            "contact_phone, rating, review_count, taste_score, environment_score, service_score, " +
            "cover_image, images, menu_images, menu_items, recommended_dishes, facilities, services, " +
            "dietary_options, suitable_occasions, reservation_required, booking_platforms, tags, data_source, last_verified_at) " +
            "VALUES (#{amapPoiId}, #{name}, #{cuisineType}, #{category}, #{address}, #{city}, #{latitude}, #{longitude}, " +
            "#{description}, #{specialties}, #{atmosphere}, #{priceLevel}, #{averageCost}, #{businessHours}, #{breakTime}, " +
            "#{contactPhone}, #{rating}, #{reviewCount}, #{tasteScore}, #{environmentScore}, #{serviceScore}, " +
            "#{coverImage}, #{images}, #{menuImages}, #{menuItems}, #{recommendedDishes}, #{facilities}, #{services}, " +
            "#{dietaryOptions}, #{suitableOccasions}, #{reservationRequired}, #{bookingPlatforms}, #{tags}, #{dataSource}, #{lastVerifiedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(FoodDetail foodDetail);
    
    /**
     * 更新美食详情
     *
     * @param foodDetail 美食详情对象
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE food_details " +
            "<set>" +
            "  <if test='amapPoiId != null'>amap_poi_id = #{amapPoiId},</if>" +
            "  <if test='name != null'>name = #{name},</if>" +
            "  <if test='cuisineType != null'>cuisine_type = #{cuisineType},</if>" +
            "  <if test='category != null'>category = #{category},</if>" +
            "  <if test='address != null'>address = #{address},</if>" +
            "  <if test='city != null'>city = #{city},</if>" +
            "  <if test='latitude != null'>latitude = #{latitude},</if>" +
            "  <if test='longitude != null'>longitude = #{longitude},</if>" +
            "  <if test='description != null'>description = #{description},</if>" +
            "  <if test='specialties != null'>specialties = #{specialties},</if>" +
            "  <if test='atmosphere != null'>atmosphere = #{atmosphere},</if>" +
            "  <if test='priceLevel != null'>price_level = #{priceLevel},</if>" +
            "  <if test='averageCost != null'>average_cost = #{averageCost},</if>" +
            "  <if test='businessHours != null'>business_hours = #{businessHours},</if>" +
            "  <if test='breakTime != null'>break_time = #{breakTime},</if>" +
            "  <if test='contactPhone != null'>contact_phone = #{contactPhone},</if>" +
            "  <if test='rating != null'>rating = #{rating},</if>" +
            "  <if test='reviewCount != null'>review_count = #{reviewCount},</if>" +
            "  <if test='tasteScore != null'>taste_score = #{tasteScore},</if>" +
            "  <if test='environmentScore != null'>environment_score = #{environmentScore},</if>" +
            "  <if test='serviceScore != null'>service_score = #{serviceScore},</if>" +
            "  <if test='coverImage != null'>cover_image = #{coverImage},</if>" +
            "  <if test='images != null'>images = #{images},</if>" +
            "  <if test='menuImages != null'>menu_images = #{menuImages},</if>" +
            "  <if test='menuItems != null'>menu_items = #{menuItems},</if>" +
            "  <if test='recommendedDishes != null'>recommended_dishes = #{recommendedDishes},</if>" +
            "  <if test='facilities != null'>facilities = #{facilities},</if>" +
            "  <if test='services != null'>services = #{services},</if>" +
            "  <if test='dietaryOptions != null'>dietary_options = #{dietaryOptions},</if>" +
            "  <if test='suitableOccasions != null'>suitable_occasions = #{suitableOccasions},</if>" +
            "  <if test='reservationRequired != null'>reservation_required = #{reservationRequired},</if>" +
            "  <if test='bookingPlatforms != null'>booking_platforms = #{bookingPlatforms},</if>" +
            "  <if test='tags != null'>tags = #{tags},</if>" +
            "  <if test='dataSource != null'>data_source = #{dataSource},</if>" +
            "  <if test='lastVerifiedAt != null'>last_verified_at = #{lastVerifiedAt},</if>" +
            "  updated_at = NOW() " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int update(FoodDetail foodDetail);
    
    /**
     * 删除美食详情
     *
     * @param id 美食详情ID
     * @return 影响行数
     */
    @Delete("DELETE FROM food_details WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
} 
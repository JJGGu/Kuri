package com.kuri.backend.location.mapper;

import com.kuri.backend.location.model.Location;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 地点Mapper接口
 */
@Mapper
public interface LocationMapper {
    
    /**
     * 根据ID查询地点
     *
     * @param id 地点ID
     * @return 地点对象
     */
    @Select("SELECT * FROM locations WHERE id = #{id}")
    Location selectById(@Param("id") Long id);
    
    /**
     * 查询所有地点
     *
     * @return 地点列表
     */
    @Select("SELECT * FROM locations WHERE is_active = true")
    List<Location> selectAll();
    
    /**
     * 根据城市查询地点
     *
     * @param city 城市名称
     * @return 地点列表
     */
    @Select("SELECT * FROM locations WHERE city = #{city} AND is_active = true")
    List<Location> selectByCity(@Param("city") String city);
    
    /**
     * 根据分类查询地点
     *
     * @param category 分类
     * @return 地点列表
     */
    @Select("SELECT * FROM locations WHERE category = #{category} AND is_active = true")
    List<Location> selectByCategory(@Param("category") String category);
    
    /**
     * 根据名称模糊查询地点
     *
     * @param name 地点名称
     * @return 地点列表
     */
    @Select("SELECT * FROM locations WHERE name LIKE CONCAT('%', #{name}, '%') AND is_active = true")
    List<Location> selectByNameLike(@Param("name") String name);
    
    /**
     * 插入地点
     *
     * @param location 地点对象
     * @return 影响行数
     */
    @Insert("INSERT INTO locations (name, description, address, latitude, longitude, image_url, category, city, province, country) " +
            "VALUES (#{name}, #{description}, #{address}, #{latitude}, #{longitude}, #{imageUrl}, #{category}, #{city}, #{province}, #{country})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Location location);
    
    /**
     * 更新地点
     *
     * @param location 地点对象
     * @return 影响行数
     */
    @Update("UPDATE locations SET name = #{name}, description = #{description}, address = #{address}, " +
            "latitude = #{latitude}, longitude = #{longitude}, image_url = #{imageUrl}, category = #{category}, " +
            "city = #{city}, province = #{province}, country = #{country}, updated_at = NOW() " +
            "WHERE id = #{id}")
    int update(Location location);
    
    /**
     * 删除地点（逻辑删除）
     *
     * @param id 地点ID
     * @return 影响行数
     */
    @Update("UPDATE locations SET is_active = false, updated_at = NOW() WHERE id = #{id}")
    int delete(@Param("id") Long id);
    
    /**
     * 根据经纬度范围查询地点
     *
     * @param minLat 最小纬度
     * @param maxLat 最大纬度
     * @param minLng 最小经度
     * @param maxLng 最大经度
     * @return 地点列表
     */
    @Select("SELECT * FROM locations WHERE latitude BETWEEN #{minLat} AND #{maxLat} " +
            "AND longitude BETWEEN #{minLng} AND #{maxLng} AND is_active = true")
    List<Location> selectByCoordinateRange(
            @Param("minLat") double minLat, 
            @Param("maxLat") double maxLat, 
            @Param("minLng") double minLng, 
            @Param("maxLng") double maxLng);
    
    /**
     * 增加地点浏览量
     *
     * @param id 地点ID
     * @return 影响行数
     */
    @Update("UPDATE locations SET view_count = view_count + 1 WHERE id = #{id}")
    int incrementViewCount(@Param("id") Long id);
    
    /**
     * 更新地点评分
     *
     * @param id 地点ID
     * @param rating 评分
     * @return 影响行数
     */
    @Update("UPDATE locations SET rating = #{rating}, updated_at = NOW() WHERE id = #{id}")
    int updateRating(@Param("id") Long id, @Param("rating") double rating);
} 
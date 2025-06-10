package com.kuri.backend.trip.mapper;

import com.kuri.backend.trip.model.Trip;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * 旅行Mapper接口
 */
@Mapper
public interface TripMapper {
    
    /**
     * 根据ID查询旅行
     *
     * @param id 旅行ID
     * @return 旅行对象
     */
    @Select("SELECT * FROM trips WHERE id = #{id} AND deleted_at IS NULL")
    Trip selectById(@Param("id") Long id);
    
    /**
     * 查询所有公开的旅行
     *
     * @return 旅行列表
     */
    @Select("SELECT * FROM trips WHERE is_public = true AND status != 'Cancelled' AND deleted_at IS NULL ORDER BY created_at DESC")
    List<Trip> selectAllPublic();
    
    /**
     * 根据用户ID查询旅行
     *
     * @param userId 用户ID
     * @return 旅行列表
     */
    @Select("SELECT * FROM trips WHERE owner_user_id = #{userId} AND deleted_at IS NULL ORDER BY created_at DESC")
    List<Trip> selectByUserId(@Param("userId") Long userId);
    
    /**
     * 根据状态查询旅行
     *
     * @param status 状态
     * @return 旅行列表
     */
    @Select("SELECT * FROM trips WHERE status = #{status} AND deleted_at IS NULL ORDER BY start_date")
    List<Trip> selectByStatus(@Param("status") String status);
    
    /**
     * 根据日期范围查询旅行
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 旅行列表
     */
    @Select("SELECT * FROM trips WHERE " +
            "((start_date BETWEEN #{startDate} AND #{endDate}) OR " +
            "(end_date BETWEEN #{startDate} AND #{endDate}) OR " +
            "(start_date <= #{startDate} AND end_date >= #{endDate})) " +
            "AND deleted_at IS NULL ORDER BY start_date")
    List<Trip> selectByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
    /**
     * 根据目的地名称查询旅行
     *
     * @param destination 目的地名称
     * @return 旅行列表
     */
    @Select("SELECT * FROM trips WHERE JSON_SEARCH(destination_names, 'one', #{destination}) IS NOT NULL " +
            "AND deleted_at IS NULL ORDER BY created_at DESC")
    List<Trip> selectByDestination(@Param("destination") String destination);
    
    /**
     * 插入旅行
     *
     * @param trip 旅行对象
     * @return 影响行数
     */
    @Insert("INSERT INTO trips (owner_user_id, title, description, cover_image, departure_location_id, " +
            "departure_name, destination_location_ids, destination_names, start_date, end_date, days, " +
            "travel_mode, preferences, budget, estimated_cost, status, is_public, generation_status, " +
            "style, people_count) " +
            "VALUES (#{ownerUserId}, #{title}, #{description}, #{coverImage}, #{departureLocationId}, " +
            "#{departureName}, #{destinationLocationIds}, #{destinationNames}, #{startDate}, #{endDate}, " +
            "#{days}, #{travelMode}, #{preferences}, #{budget}, #{estimatedCost}, #{status}, #{isPublic}, " +
            "#{generationStatus}, #{style}, #{peopleCount})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Trip trip);
    
    /**
     * 更新旅行
     *
     * @param trip 旅行对象
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE trips " +
            "<set>" +
            "  <if test='title != null'>title = #{title},</if>" +
            "  <if test='description != null'>description = #{description},</if>" +
            "  <if test='coverImage != null'>cover_image = #{coverImage},</if>" +
            "  <if test='departureLocationId != null'>departure_location_id = #{departureLocationId},</if>" +
            "  <if test='departureName != null'>departure_name = #{departureName},</if>" +
            "  <if test='destinationLocationIds != null'>destination_location_ids = #{destinationLocationIds},</if>" +
            "  <if test='destinationNames != null'>destination_names = #{destinationNames},</if>" +
            "  <if test='startDate != null'>start_date = #{startDate},</if>" +
            "  <if test='endDate != null'>end_date = #{endDate},</if>" +
            "  <if test='days != null'>days = #{days},</if>" +
            "  <if test='travelMode != null'>travel_mode = #{travelMode},</if>" +
            "  <if test='preferences != null'>preferences = #{preferences},</if>" +
            "  <if test='budget != null'>budget = #{budget},</if>" +
            "  <if test='estimatedCost != null'>estimated_cost = #{estimatedCost},</if>" +
            "  <if test='status != null'>status = #{status},</if>" +
            "  <if test='isPublic != null'>is_public = #{isPublic},</if>" +
            "  <if test='generationStatus != null'>generation_status = #{generationStatus},</if>" +
            "  <if test='style != null'>style = #{style},</if>" +
            "  <if test='peopleCount != null'>people_count = #{peopleCount},</if>" +
            "  updated_at = NOW()" +
            "</set>" +
            "WHERE id = #{id} AND deleted_at IS NULL" +
            "</script>")
    int update(Trip trip);
    
    /**
     * 删除旅行（软删除）
     *
     * @param id 旅行ID
     * @return 影响行数
     */
    @Update("UPDATE trips SET deleted_at = NOW() WHERE id = #{id}")
    int delete(@Param("id") Long id);
    
    /**
     * 更新旅行状态
     *
     * @param id 旅行ID
     * @param status 状态
     * @return 影响行数
     */
    @Update("UPDATE trips SET status = #{status}, updated_at = NOW() WHERE id = #{id} AND deleted_at IS NULL")
    int updateStatus(@Param("id") Long id, @Param("status") String status);
    
    /**
     * 更新旅行生成状态
     *
     * @param id 旅行ID
     * @param generationStatus 生成状态
     * @return 影响行数
     */
    @Update("UPDATE trips SET generation_status = #{generationStatus}, updated_at = NOW() WHERE id = #{id} AND deleted_at IS NULL")
    int updateGenerationStatus(@Param("id") Long id, @Param("generationStatus") Integer generationStatus);
    
    /**
     * 增加旅行浏览量
     *
     * @param id 旅行ID
     * @return 影响行数
     */
    @Update("UPDATE trips SET view_count = view_count + 1 WHERE id = #{id} AND deleted_at IS NULL")
    int incrementViewCount(@Param("id") Long id);
    
    /**
     * 增加旅行点赞量
     *
     * @param id 旅行ID
     * @return 影响行数
     */
    @Update("UPDATE trips SET like_count = like_count + 1 WHERE id = #{id} AND deleted_at IS NULL")
    int incrementLikeCount(@Param("id") Long id);
    
    /**
     * 增加旅行分享量
     *
     * @param id 旅行ID
     * @return 影响行数
     */
    @Update("UPDATE trips SET share_count = share_count + 1 WHERE id = #{id} AND deleted_at IS NULL")
    int incrementShareCount(@Param("id") Long id);
} 
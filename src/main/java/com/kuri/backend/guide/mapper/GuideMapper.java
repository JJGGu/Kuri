package com.kuri.backend.guide.mapper;

import com.kuri.backend.guide.entity.Guide;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 攻略Mapper接口
 */
@Mapper
public interface GuideMapper {
    /**
     * 根据ID查询攻略
     *
     * @param id 攻略ID
     * @return 攻略对象
     */
    @Select("SELECT * FROM guides WHERE id = #{id} AND deleted_at IS NULL")
    Guide selectById(@Param("id") Long id);

    /**
     * 查询所有攻略
     *
     * @return 攻略列表
     */
    @Select("SELECT * FROM guides WHERE deleted_at IS NULL ORDER BY created_at DESC")
    List<Guide> selectAll();

    /**
     * 根据作者ID查询攻略
     *
     * @param createdBy 作者ID
     * @return 攻略列表
     */
    @Select("SELECT * FROM guides WHERE created_by = #{createdBy} AND deleted_at IS NULL ORDER BY created_at DESC")
    List<Guide> selectByAuthorId(@Param("createdBy") Long createdBy);

    /**
     * 根据分类查询攻略
     *
     * @param type 分类
     * @return 攻略列表
     */
    @Select("SELECT * FROM guides WHERE type = #{type} AND deleted_at IS NULL ORDER BY created_at DESC")
    List<Guide> selectByCategory(@Param("type") String type);
    
    /**
     * 根据城市查询攻略
     *
     * @param city 城市
     * @return 攻略列表
     */
    @Select("SELECT * FROM guides WHERE city = #{city} AND deleted_at IS NULL ORDER BY created_at DESC")
    List<Guide> selectByCity(@Param("city") String city);
    
    /**
     * 根据平台查询攻略
     *
     * @param platform 平台
     * @return 攻略列表
     */
    @Select("SELECT * FROM guides WHERE platform = #{platform} AND deleted_at IS NULL ORDER BY created_at DESC")
    List<Guide> selectByPlatform(@Param("platform") String platform);
    
    /**
     * 根据标题模糊查询攻略
     *
     * @param title 标题
     * @return 攻略列表
     */
    @Select("SELECT * FROM guides WHERE title LIKE CONCAT('%', #{title}, '%') AND deleted_at IS NULL ORDER BY created_at DESC")
    List<Guide> selectByTitleLike(@Param("title") String title);

    /**
     * 插入攻略
     *
     * @param guide 攻略对象
     * @return 影响行数
     */
    @Insert("INSERT INTO guides (title, url, cover_image, description, type, platform, city, " +
            "attractions, foods, hotels, poi_ids, content, tags, created_by) " +
            "VALUES (#{title}, #{url}, #{coverImage}, #{description}, #{type}, #{platform}, #{city}, " +
            "#{attractions}, #{foods}, #{hotels}, #{poiIds}, #{content}, #{tags}, #{createdBy})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Guide guide);

    /**
     * 更新攻略
     *
     * @param guide 攻略对象
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE guides " +
            "<set>" +
            "  <if test='title != null'>title = #{title},</if>" +
            "  <if test='url != null'>url = #{url},</if>" +
            "  <if test='coverImage != null'>cover_image = #{coverImage},</if>" +
            "  <if test='description != null'>description = #{description},</if>" +
            "  <if test='type != null'>type = #{type},</if>" +
            "  <if test='platform != null'>platform = #{platform},</if>" +
            "  <if test='city != null'>city = #{city},</if>" +
            "  <if test='attractions != null'>attractions = #{attractions},</if>" +
            "  <if test='foods != null'>foods = #{foods},</if>" +
            "  <if test='hotels != null'>hotels = #{hotels},</if>" +
            "  <if test='poiIds != null'>poi_ids = #{poiIds},</if>" +
            "  <if test='content != null'>content = #{content},</if>" +
            "  <if test='tags != null'>tags = #{tags},</if>" +
            "  updated_at = NOW() " +
            "</set>" +
            "WHERE id = #{id} AND deleted_at IS NULL" +
            "</script>")
    int update(Guide guide);

    /**
     * 删除攻略（软删除）
     *
     * @param id 攻略ID
     * @return 影响行数
     */
    @Update("UPDATE guides SET deleted_at = NOW() WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

    /**
     * 增加攻略浏览量
     *
     * @param id 攻略ID
     * @return 影响行数
     */
    @Update("UPDATE guides SET view_count = view_count + 1 WHERE id = #{id} AND deleted_at IS NULL")
    int incrementViewCount(@Param("id") Long id);
} 
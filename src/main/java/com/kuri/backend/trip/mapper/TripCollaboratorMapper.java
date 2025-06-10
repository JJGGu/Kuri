package com.kuri.backend.trip.mapper;

import com.kuri.backend.trip.model.TripCollaborator;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 行程协作者Mapper接口
 */
@Mapper
public interface TripCollaboratorMapper {
    
    /**
     * 根据ID查询行程协作者
     *
     * @param id 协作记录ID
     * @return 协作者对象
     */
    @Select("SELECT * FROM trip_collaborators WHERE id = #{id}")
    TripCollaborator selectById(@Param("id") Long id);
    
    /**
     * 根据行程ID查询所有协作者
     *
     * @param tripId 行程ID
     * @return 协作者列表
     */
    @Select("SELECT * FROM trip_collaborators WHERE trip_id = #{tripId}")
    List<TripCollaborator> selectByTripId(@Param("tripId") Long tripId);
    
    /**
     * 根据用户ID查询所有协作行程
     *
     * @param userId 用户ID
     * @return 协作者列表
     */
    @Select("SELECT * FROM trip_collaborators WHERE user_id = #{userId}")
    List<TripCollaborator> selectByUserId(@Param("userId") Long userId);
    
    /**
     * 查询用户在某行程中的角色
     *
     * @param tripId 行程ID
     * @param userId 用户ID
     * @return 协作者对象
     */
    @Select("SELECT * FROM trip_collaborators WHERE trip_id = #{tripId} AND user_id = #{userId}")
    TripCollaborator selectByTripIdAndUserId(@Param("tripId") Long tripId, @Param("userId") Long userId);
    
    /**
     * 插入行程协作者
     *
     * @param collaborator 协作者对象
     * @return 影响行数
     */
    @Insert("INSERT INTO trip_collaborators (trip_id, user_id, role, status, permissions, invited_by, invited_at) " +
            "VALUES (#{tripId}, #{userId}, #{role}, #{status}, #{permissions}, #{invitedBy}, #{invitedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(TripCollaborator collaborator);
    
    /**
     * 更新行程协作者
     *
     * @param collaborator 协作者对象
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE trip_collaborators " +
            "<set>" +
            "  <if test='role != null'>role = #{role},</if>" +
            "  <if test='status != null'>status = #{status},</if>" +
            "  <if test='permissions != null'>permissions = #{permissions},</if>" +
            "  <if test='joinedAt != null'>joined_at = #{joinedAt},</if>" +
            "  updated_at = NOW() " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int update(TripCollaborator collaborator);
    
    /**
     * 更新协作者状态
     *
     * @param id 协作记录ID
     * @param status 状态
     * @return 影响行数
     */
    @Update("UPDATE trip_collaborators SET status = #{status}, updated_at = NOW() WHERE id = #{id}")
    int updateStatus(@Param("id") Long id, @Param("status") String status);
    
    /**
     * 更新加入时间
     *
     * @param id 协作记录ID
     * @return 影响行数
     */
    @Update("UPDATE trip_collaborators SET joined_at = NOW(), status = 'Active', updated_at = NOW() WHERE id = #{id}")
    int updateJoinedTime(@Param("id") Long id);
    
    /**
     * 删除行程协作者
     *
     * @param id 协作记录ID
     * @return 影响行数
     */
    @Delete("DELETE FROM trip_collaborators WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
    
    /**
     * 删除行程的所有协作者
     *
     * @param tripId 行程ID
     * @return 影响行数
     */
    @Delete("DELETE FROM trip_collaborators WHERE trip_id = #{tripId}")
    int deleteByTripId(@Param("tripId") Long tripId);
} 
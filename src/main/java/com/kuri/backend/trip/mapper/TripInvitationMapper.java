package com.kuri.backend.trip.mapper;

import com.kuri.backend.trip.model.TripInvitation;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 行程邀请Mapper接口
 */
@Mapper
public interface TripInvitationMapper {
    
    /**
     * 根据ID查询行程邀请
     *
     * @param id 邀请ID
     * @return 邀请对象
     */
    @Select("SELECT * FROM trip_invitations WHERE id = #{id}")
    TripInvitation selectById(@Param("id") Long id);
    
    /**
     * 根据行程ID查询邀请
     *
     * @param tripId 行程ID
     * @return 邀请列表
     */
    @Select("SELECT * FROM trip_invitations WHERE trip_id = #{tripId}")
    List<TripInvitation> selectByTripId(@Param("tripId") Long tripId);
    
    /**
     * 根据邀请人ID查询邀请
     *
     * @param inviterUserId 邀请人ID
     * @return 邀请列表
     */
    @Select("SELECT * FROM trip_invitations WHERE inviter_user_id = #{inviterUserId}")
    List<TripInvitation> selectByInviterUserId(@Param("inviterUserId") Long inviterUserId);
    
    /**
     * 根据被邀请人ID查询邀请
     *
     * @param inviteeUserId 被邀请人ID
     * @return 邀请列表
     */
    @Select("SELECT * FROM trip_invitations WHERE invitee_user_id = #{inviteeUserId}")
    List<TripInvitation> selectByInviteeUserId(@Param("inviteeUserId") Long inviteeUserId);
    
    /**
     * 根据被邀请人手机号查询邀请
     *
     * @param inviteePhone 被邀请人手机号
     * @return 邀请列表
     */
    @Select("SELECT * FROM trip_invitations WHERE invitee_phone = #{inviteePhone}")
    List<TripInvitation> selectByInviteePhone(@Param("inviteePhone") String inviteePhone);
    
    /**
     * 查询未过期的待处理邀请
     *
     * @return 邀请列表
     */
    @Select("SELECT * FROM trip_invitations WHERE status = 'Pending' AND expires_at > NOW()")
    List<TripInvitation> selectPendingInvitations();
    
    /**
     * 插入行程邀请
     *
     * @param invitation 邀请对象
     * @return 影响行数
     */
    @Insert("INSERT INTO trip_invitations (trip_id, inviter_user_id, invitee_user_id, invitee_phone, role, message, status, expires_at) " +
            "VALUES (#{tripId}, #{inviterUserId}, #{inviteeUserId}, #{inviteePhone}, #{role}, #{message}, #{status}, #{expiresAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(TripInvitation invitation);
    
    /**
     * 更新行程邀请
     *
     * @param invitation 邀请对象
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE trip_invitations " +
            "<set>" +
            "  <if test='status != null'>status = #{status},</if>" +
            "  <if test='message != null'>message = #{message},</if>" +
            "  <if test='expiresAt != null'>expires_at = #{expiresAt},</if>" +
            "  <if test='processedAt != null'>processed_at = #{processedAt},</if>" +
            "  updated_at = NOW() " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int update(TripInvitation invitation);
    
    /**
     * 更新邀请状态
     *
     * @param id 邀请ID
     * @param status 状态
     * @return 影响行数
     */
    @Update("UPDATE trip_invitations SET status = #{status}, processed_at = NOW(), updated_at = NOW() WHERE id = #{id}")
    int updateStatus(@Param("id") Long id, @Param("status") String status);
    
    /**
     * 删除行程邀请
     *
     * @param id 邀请ID
     * @return 影响行数
     */
    @Delete("DELETE FROM trip_invitations WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
    
    /**
     * 删除行程的所有邀请
     *
     * @param tripId 行程ID
     * @return 影响行数
     */
    @Delete("DELETE FROM trip_invitations WHERE trip_id = #{tripId}")
    int deleteByTripId(@Param("tripId") Long tripId);
} 
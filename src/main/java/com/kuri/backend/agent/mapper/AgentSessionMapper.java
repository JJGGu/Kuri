package com.kuri.backend.agent.mapper;

import com.kuri.backend.agent.model.AgentSession;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * AI助手会话Mapper接口
 */
@Mapper
public interface AgentSessionMapper {
    
    /**
     * 根据ID查询会话
     *
     * @param id 会话ID
     * @return 会话对象
     */
    @Select("SELECT * FROM agent_sessions WHERE id = #{id}")
    AgentSession selectById(@Param("id") Long id);
    
    /**
     * 根据会话ID查询会话
     *
     * @param sessionId 会话唯一标识
     * @return 会话对象
     */
    @Select("SELECT * FROM agent_sessions WHERE session_id = #{sessionId}")
    AgentSession selectBySessionId(@Param("sessionId") String sessionId);
    
    /**
     * 根据用户ID查询会话列表
     *
     * @param userId 用户ID
     * @return 会话列表
     */
    @Select("SELECT * FROM agent_sessions WHERE user_id = #{userId} ORDER BY updated_at DESC")
    List<AgentSession> selectByUserId(@Param("userId") Long userId);
    
    /**
     * 查询用户的活跃会话
     *
     * @param userId 用户ID
     * @return 活跃会话列表
     */
    @Select("SELECT * FROM agent_sessions WHERE user_id = #{userId} AND status = 1 ORDER BY updated_at DESC")
    List<AgentSession> selectActiveByUserId(@Param("userId") Long userId);
    
    /**
     * 插入会话
     *
     * @param session 会话对象
     * @return 影响行数
     */
    @Insert("INSERT INTO agent_sessions (user_id, session_id, title, status) " +
            "VALUES (#{userId}, #{sessionId}, #{title}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(AgentSession session);
    
    /**
     * 更新会话
     *
     * @param session 会话对象
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE agent_sessions " +
            "<set>" +
            "  <if test='title != null'>title = #{title},</if>" +
            "  <if test='status != null'>status = #{status},</if>" +
            "  updated_at = NOW() " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int update(AgentSession session);
    
    /**
     * 更新会话标题
     *
     * @param id 会话ID
     * @param title 标题
     * @return 影响行数
     */
    @Update("UPDATE agent_sessions SET title = #{title}, updated_at = NOW() WHERE id = #{id}")
    int updateTitle(@Param("id") Long id, @Param("title") String title);
    
    /**
     * 更新会话状态
     *
     * @param id 会话ID
     * @param status 状态（0：关闭，1：活跃）
     * @return 影响行数
     */
    @Update("UPDATE agent_sessions SET status = #{status}, updated_at = NOW() WHERE id = #{id}")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    
    /**
     * 删除会话
     *
     * @param id 会话ID
     * @return 影响行数
     */
    @Delete("DELETE FROM agent_sessions WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
    
    /**
     * 关闭超过一定时间未活动的会话
     *
     * @param hours 小时数
     * @return 影响行数
     */
    @Update("UPDATE agent_sessions SET status = 0, updated_at = NOW() " +
            "WHERE status = 1 AND updated_at < DATE_SUB(NOW(), INTERVAL #{hours} HOUR)")
    int closeInactiveSessions(@Param("hours") int hours);
} 
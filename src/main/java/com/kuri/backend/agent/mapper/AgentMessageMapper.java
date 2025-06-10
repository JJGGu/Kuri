package com.kuri.backend.agent.mapper;

import com.kuri.backend.agent.model.AgentMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * AI助手消息Mapper接口
 */
@Mapper
public interface AgentMessageMapper {
    
    /**
     * 根据ID查询消息
     *
     * @param id 消息ID
     * @return 消息对象
     */
    @Select("SELECT * FROM agent_messages WHERE id = #{id}")
    AgentMessage selectById(@Param("id") Long id);
    
    /**
     * 根据会话ID查询消息
     *
     * @param sessionId 会话ID
     * @return 消息列表
     */
    @Select("SELECT * FROM agent_messages WHERE session_id = #{sessionId} ORDER BY created_at")
    List<AgentMessage> selectBySessionId(@Param("sessionId") Long sessionId);
    
    /**
     * 根据会话ID分页查询消息
     *
     * @param sessionId 会话ID
     * @param offset 偏移量
     * @param limit 限制条数
     * @return 消息列表
     */
    @Select("SELECT * FROM agent_messages WHERE session_id = #{sessionId} ORDER BY created_at DESC LIMIT #{offset}, #{limit}")
    List<AgentMessage> selectBySessionIdWithPage(
            @Param("sessionId") Long sessionId, 
            @Param("offset") int offset, 
            @Param("limit") int limit);
    
    /**
     * 插入消息
     *
     * @param message 消息对象
     * @return 影响行数
     */
    @Insert("INSERT INTO agent_messages (session_id, message_type, content) " +
            "VALUES (#{sessionId}, #{messageType}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(AgentMessage message);
    
    /**
     * 批量插入消息
     *
     * @param messages 消息对象列表
     * @return 影响行数
     */
    @Insert("<script>" +
            "INSERT INTO agent_messages (session_id, message_type, content) VALUES " +
            "<foreach collection='list' item='message' separator=','>" +
            "(#{message.sessionId}, #{message.messageType}, #{message.content})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("list") List<AgentMessage> messages);
    
    /**
     * 更新消息内容
     *
     * @param id 消息ID
     * @param content 消息内容
     * @return 影响行数
     */
    @Update("UPDATE agent_messages SET content = #{content} WHERE id = #{id}")
    int updateContent(@Param("id") Long id, @Param("content") String content);
    
    /**
     * 删除消息
     *
     * @param id 消息ID
     * @return 影响行数
     */
    @Delete("DELETE FROM agent_messages WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
    
    /**
     * 根据会话ID删除所有消息
     *
     * @param sessionId 会话ID
     * @return 影响行数
     */
    @Delete("DELETE FROM agent_messages WHERE session_id = #{sessionId}")
    int deleteBySessionId(@Param("sessionId") Long sessionId);
    
    /**
     * 统计会话消息数量
     *
     * @param sessionId 会话ID
     * @return 消息数量
     */
    @Select("SELECT COUNT(*) FROM agent_messages WHERE session_id = #{sessionId}")
    int countBySessionId(@Param("sessionId") Long sessionId);
} 
package com.kuri.backend.agent.mapper;

import com.kuri.backend.agent.model.AiModelCall;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * AI模型调用记录Mapper接口
 */
@Mapper
public interface AiModelCallMapper {
    
    /**
     * 根据ID查询调用记录
     *
     * @param id 调用记录ID
     * @return 调用记录对象
     */
    @Select("SELECT * FROM ai_model_calls WHERE id = #{id}")
    AiModelCall selectById(@Param("id") Long id);
    
    /**
     * 根据用户ID查询调用记录
     *
     * @param userId 用户ID
     * @return 调用记录列表
     */
    @Select("SELECT * FROM ai_model_calls WHERE user_id = #{userId} ORDER BY created_at DESC")
    List<AiModelCall> selectByUserId(@Param("userId") Long userId);
    
    /**
     * 根据行程ID查询调用记录
     *
     * @param tripId 行程ID
     * @return 调用记录列表
     */
    @Select("SELECT * FROM ai_model_calls WHERE trip_id = #{tripId} ORDER BY created_at DESC")
    List<AiModelCall> selectByTripId(@Param("tripId") Long tripId);
    
    /**
     * 根据调用类型查询记录
     *
     * @param callType 调用类型
     * @return 调用记录列表
     */
    @Select("SELECT * FROM ai_model_calls WHERE call_type = #{callType} ORDER BY created_at DESC")
    List<AiModelCall> selectByCallType(@Param("callType") String callType);
    
    /**
     * 根据状态查询记录
     *
     * @param status 状态
     * @return 调用记录列表
     */
    @Select("SELECT * FROM ai_model_calls WHERE status = #{status} ORDER BY created_at DESC")
    List<AiModelCall> selectByStatus(@Param("status") Integer status);
    
    /**
     * 插入调用记录
     *
     * @param aiModelCall 调用记录对象
     * @return 影响行数
     */
    @Insert("INSERT INTO ai_model_calls (user_id, trip_id, call_type, model_name, prompt, response, status, error_message, tokens_used, cost, duration_ms) " +
            "VALUES (#{userId}, #{tripId}, #{callType}, #{modelName}, #{prompt}, #{response}, #{status}, #{errorMessage}, #{tokensUsed}, #{cost}, #{durationMs})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(AiModelCall aiModelCall);
    
    /**
     * 更新调用记录
     *
     * @param aiModelCall 调用记录对象
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE ai_model_calls " +
            "<set>" +
            "  <if test='response != null'>response = #{response},</if>" +
            "  <if test='status != null'>status = #{status},</if>" +
            "  <if test='errorMessage != null'>error_message = #{errorMessage},</if>" +
            "  <if test='tokensUsed != null'>tokens_used = #{tokensUsed},</if>" +
            "  <if test='cost != null'>cost = #{cost},</if>" +
            "  <if test='durationMs != null'>duration_ms = #{durationMs},</if>" +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int update(AiModelCall aiModelCall);
    
    /**
     * 更新调用状态
     *
     * @param id 调用记录ID
     * @param status 状态
     * @param errorMessage 错误信息
     * @return 影响行数
     */
    @Update("UPDATE ai_model_calls SET status = #{status}, error_message = #{errorMessage} WHERE id = #{id}")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status, @Param("errorMessage") String errorMessage);
    
    /**
     * 删除调用记录
     *
     * @param id 调用记录ID
     * @return 影响行数
     */
    @Delete("DELETE FROM ai_model_calls WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
    
    /**
     * 删除用户的调用记录
     *
     * @param userId 用户ID
     * @return 影响行数
     */
    @Delete("DELETE FROM ai_model_calls WHERE user_id = #{userId}")
    int deleteByUserId(@Param("userId") Long userId);
} 
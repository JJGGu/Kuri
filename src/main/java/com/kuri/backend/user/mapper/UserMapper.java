package com.kuri.backend.user.mapper;

import com.kuri.backend.user.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户Mapper接口
 */
@Mapper
public interface UserMapper {
    
    /**
     * 根据ID查询用户
     *
     * @param id 用户ID
     * @return 用户对象
     */
    @Select("SELECT * FROM users WHERE id = #{id}")
    User selectById(@Param("id") Long id);
    
    /**
     * 根据OpenID查询用户
     *
     * @param openId 微信OpenID
     * @return 用户对象
     */
    @Select("SELECT * FROM users WHERE open_id = #{openId}")
    User selectByOpenId(@Param("openId") String openId);
    
    /**
     * 根据UnionID查询用户
     *
     * @param unionId 微信UnionID
     * @return 用户对象
     */
    @Select("SELECT * FROM users WHERE union_id = #{unionId}")
    User selectByUnionId(@Param("unionId") String unionId);
    
    /**
     * 根据手机号查询用户
     *
     * @param phone 手机号
     * @return 用户对象
     */
    @Select("SELECT * FROM users WHERE phone = #{phone}")
    User selectByPhone(@Param("phone") String phone);
    
    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    @Select("SELECT * FROM users WHERE status = 1")
    List<User> selectAll();
    
    /**
     * 插入用户
     *
     * @param user 用户对象
     * @return 影响行数
     */
    @Insert("INSERT INTO users (open_id, union_id, nickname, avatar_url, phone, gender, country, province, city, vip_level, status) " +
            "VALUES (#{openId}, #{unionId}, #{nickname}, #{avatarUrl}, #{phone}, #{gender}, #{country}, #{province}, #{city}, #{vipLevel}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);
    
    /**
     * 更新用户
     *
     * @param user 用户对象
     * @return 影响行数
     */
    @Update("<script>" +
            "UPDATE users " +
            "<set>" +
            "  <if test='nickname != null'>nickname = #{nickname},</if>" +
            "  <if test='avatarUrl != null'>avatar_url = #{avatarUrl},</if>" +
            "  <if test='phone != null'>phone = #{phone},</if>" +
            "  <if test='gender != null'>gender = #{gender},</if>" +
            "  <if test='country != null'>country = #{country},</if>" +
            "  <if test='province != null'>province = #{province},</if>" +
            "  <if test='city != null'>city = #{city},</if>" +
            "  <if test='vipLevel != null'>vip_level = #{vipLevel},</if>" +
            "  <if test='status != null'>status = #{status},</if>" +
            "  updated_at = NOW() " +
            "</set>" +
            "WHERE id = #{id}" +
            "</script>")
    int update(User user);
    
    /**
     * 更新用户状态
     *
     * @param id 用户ID
     * @param status 状态（0：禁用，1：正常）
     * @return 影响行数
     */
    @Update("UPDATE users SET status = #{status}, updated_at = NOW() WHERE id = #{id}")
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
    
    /**
     * 更新用户最后登录时间
     *
     * @param id 用户ID
     * @return 影响行数
     */
    @Update("UPDATE users SET last_login_at = NOW(), updated_at = NOW() WHERE id = #{id}")
    int updateLastLoginTime(@Param("id") Long id);
    
    /**
     * 删除用户（实际上我们通常不会真正删除用户记录，而是通过状态字段标记）
     *
     * @param id 用户ID
     * @return 影响行数
     */
    @Update("UPDATE users SET status = 0, updated_at = NOW() WHERE id = #{id}")
    int deleteById(@Param("id") Long id);
} 
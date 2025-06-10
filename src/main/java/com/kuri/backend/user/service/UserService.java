package com.kuri.backend.user.service;

import com.kuri.backend.common.model.PageResult;
import com.kuri.backend.user.dto.UserDTO;
import com.kuri.backend.user.model.User;

/**
 * 用户服务接口
 */
public interface UserService {
    
    /**
     * 根据ID查询用户
     *
     * @param id 用户ID
     * @return 用户DTO
     */
    UserDTO getUserById(Long id);
    
    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户实体
     */
    User getUserByUsername(String username);
    
    /**
     * 分页查询用户列表
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 分页结果
     */
    PageResult<UserDTO> getUserList(Integer pageNum, Integer pageSize);
    
    /**
     * 创建用户
     *
     * @param user 用户实体
     * @return 用户ID
     */
    Long createUser(User user);
    
    /**
     * 更新用户
     *
     * @param user 用户实体
     * @return 是否成功
     */
    boolean updateUser(User user);
    
    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 是否成功
     */
    boolean deleteUser(Long id);
} 
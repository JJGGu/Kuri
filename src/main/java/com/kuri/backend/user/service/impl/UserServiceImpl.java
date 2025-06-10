package com.kuri.backend.user.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kuri.backend.common.exception.BusinessException;
import com.kuri.backend.common.model.PageResult;
import com.kuri.backend.common.model.ResultCode;
import com.kuri.backend.user.dto.UserDTO;
import com.kuri.backend.user.mapper.UserMapper;
import com.kuri.backend.user.model.User;
import com.kuri.backend.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDTO getUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException(ResultCode.NOT_FOUND, "用户不存在");
        }
        return convertToDTO(user);
    }
    
    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
    
    @Override
    public PageResult<UserDTO> getUserList(Integer pageNum, Integer pageSize) {
        Page<User> page = PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectAll();
        
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(convertToDTO(user));
        }
        
        return PageResult.of(userDTOs, page.getTotal(), pageNum, pageSize);
    }
    
    @Override
    @Transactional
    public Long createUser(User user) {
        // 检查用户名是否已存在
        if (userMapper.selectByUsername(user.getUsername()) != null) {
            throw new BusinessException(ResultCode.PARAM_ERROR, "用户名已存在");
        }
        
        // 检查手机号是否已存在
        if (user.getPhone() != null && userMapper.selectByPhone(user.getPhone()) != null) {
            throw new BusinessException(ResultCode.PARAM_ERROR, "手机号已存在");
        }
        
        // 检查邮箱是否已存在
        if (user.getEmail() != null && userMapper.selectByEmail(user.getEmail()) != null) {
            throw new BusinessException(ResultCode.PARAM_ERROR, "邮箱已存在");
        }
        
        // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // 设置默认值
        user.setStatus(1);
        user.setDeleted(0);
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        
        userMapper.insert(user);
        return user.getId();
    }
    
    @Override
    @Transactional
    public boolean updateUser(User user) {
        User existingUser = userMapper.selectById(user.getId());
        if (existingUser == null) {
            throw new BusinessException(ResultCode.NOT_FOUND, "用户不存在");
        }
        
        // 检查用户名是否已存在
        if (user.getUsername() != null && !user.getUsername().equals(existingUser.getUsername())) {
            if (userMapper.selectByUsername(user.getUsername()) != null) {
                throw new BusinessException(ResultCode.PARAM_ERROR, "用户名已存在");
            }
        }
        
        // 检查手机号是否已存在
        if (user.getPhone() != null && !user.getPhone().equals(existingUser.getPhone())) {
            if (userMapper.selectByPhone(user.getPhone()) != null) {
                throw new BusinessException(ResultCode.PARAM_ERROR, "手机号已存在");
            }
        }
        
        // 检查邮箱是否已存在
        if (user.getEmail() != null && !user.getEmail().equals(existingUser.getEmail())) {
            if (userMapper.selectByEmail(user.getEmail()) != null) {
                throw new BusinessException(ResultCode.PARAM_ERROR, "邮箱已存在");
            }
        }
        
        // 如果密码不为空，则加密
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.update(user) > 0;
    }
    
    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException(ResultCode.NOT_FOUND, "用户不存在");
        }
        return userMapper.deleteById(id) > 0;
    }
    
    /**
     * 将用户实体转换为DTO
     *
     * @param user 用户实体
     * @return 用户DTO
     */
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
} 
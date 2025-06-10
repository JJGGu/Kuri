package com.kuri.backend.auth.service;

import com.kuri.backend.auth.dto.LoginRequest;
import com.kuri.backend.auth.dto.LoginResponse;
import com.kuri.backend.auth.dto.RegisterRequest;
import com.kuri.backend.user.model.User;

/**
 * 认证服务接口
 */
public interface AuthService {
    
    /**
     * 用户登录
     *
     * @param loginRequest 登录请求
     * @return 登录响应
     */
    LoginResponse login(LoginRequest loginRequest);
    
    /**
     * 用户注册
     *
     * @param registerRequest 注册请求
     * @return 用户ID
     */
    Long register(RegisterRequest registerRequest);
    
    /**
     * 注册请求转换为用户实体
     *
     * @param registerRequest 注册请求
     * @return 用户实体
     */
    User convertToUser(RegisterRequest registerRequest);
} 
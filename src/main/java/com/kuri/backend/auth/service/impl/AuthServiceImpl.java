package com.kuri.backend.auth.service.impl;

import com.kuri.backend.auth.dto.LoginRequest;
import com.kuri.backend.auth.dto.LoginResponse;
import com.kuri.backend.auth.dto.RegisterRequest;
import com.kuri.backend.auth.service.AuthService;
import com.kuri.backend.auth.util.JwtUtil;
import com.kuri.backend.common.exception.BusinessException;
import com.kuri.backend.common.model.ResultCode;
import com.kuri.backend.user.model.User;
import com.kuri.backend.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * 认证服务实现类
 */
@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserService userService;
    
    @Value("${jwt.expiration:86400}")
    private Long expiration;
    
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            // 验证用户名和密码
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            
            // 获取认证用户
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            
            // 生成JWT令牌
            String token = jwtUtil.generateToken(userDetails);
            
            // 获取用户信息
            User user = userService.getUserByUsername(loginRequest.getUsername());
            
            // 构建登录响应
            return new LoginResponse(
                    token,
                    "Bearer",
                    expiration,
                    user.getId(),
                    user.getUsername()
            );
        } catch (BadCredentialsException e) {
            throw new BusinessException(ResultCode.UNAUTHORIZED, "用户名或密码错误");
        }
    }
    
    @Override
    public Long register(RegisterRequest registerRequest) {
        // 转换为用户实体
        User user = convertToUser(registerRequest);
        
        // 创建用户
        return userService.createUser(user);
    }
    
    @Override
    public User convertToUser(RegisterRequest registerRequest) {
        User user = new User();
        BeanUtils.copyProperties(registerRequest, user);
        
        // 设置默认昵称
        if (user.getNickname() == null) {
            user.setNickname(registerRequest.getUsername());
        }
        
        // 设置默认性别
        user.setGender(0);
        
        return user;
    }
} 
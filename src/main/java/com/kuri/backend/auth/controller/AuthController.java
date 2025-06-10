package com.kuri.backend.auth.controller;

import com.kuri.backend.auth.dto.LoginRequest;
import com.kuri.backend.auth.dto.LoginResponse;
import com.kuri.backend.auth.dto.RegisterRequest;
import com.kuri.backend.auth.service.AuthService;
import com.kuri.backend.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

/**
 * 认证控制器
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return Result.success(authService.login(loginRequest));
    }
    
    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<Long> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return Result.success(authService.register(registerRequest));
    }
} 
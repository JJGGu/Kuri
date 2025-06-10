package com.kuri.backend.user.controller;

import com.kuri.backend.common.model.PageResult;
import com.kuri.backend.common.model.Result;
import com.kuri.backend.user.dto.UserDTO;
import com.kuri.backend.user.model.User;
import com.kuri.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    /**
     * 获取用户信息
     */
    @GetMapping("/{id}")
    public Result<UserDTO> getUserById(@PathVariable Long id) {
        return Result.success(userService.getUserById(id));
    }
    
    /**
     * 获取用户列表
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Result<PageResult<UserDTO>> getUserList(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(userService.getUserList(pageNum, pageSize));
    }
    
    /**
     * 创建用户
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Result<Long> createUser(@Valid @RequestBody User user) {
        return Result.success(userService.createUser(user));
    }
    
    /**
     * 更新用户
     */
    @PutMapping("/{id}")
    public Result<Boolean> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        user.setId(id);
        return Result.success(userService.updateUser(user));
    }
    
    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Result<Boolean> deleteUser(@PathVariable Long id) {
        return Result.success(userService.deleteUser(id));
    }
} 
package com.kuri.backend.common.constants;

/**
 * 响应状态码常量
 */
public interface ResultCode {
    /**
     * 成功
     */
    int SUCCESS = 200;
    
    /**
     * 参数错误
     */
    int PARAM_ERROR = 400;
    
    /**
     * 未授权
     */
    int UNAUTHORIZED = 401;
    
    /**
     * 禁止访问
     */
    int FORBIDDEN = 403;
    
    /**
     * 资源不存在
     */
    int NOT_FOUND = 404;
    
    /**
     * 服务器内部错误
     */
    int SERVER_ERROR = 500;
} 
package com.kuri.backend.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan("com.kuri.backend.*.mapper")
public class MyBatisConfig {
} 
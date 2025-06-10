package com.kuri.backend.common.utils;

import java.util.UUID;

/**
 * 字符串工具类
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 字符串
     * @return 是否不为空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空白
     *
     * @param str 字符串
     * @return 是否为空白
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 判断字符串是否不为空白
     *
     * @param str 字符串
     * @return 是否不为空白
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 生成UUID（无连字符）
     *
     * @return UUID字符串
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 截取字符串
     *
     * @param str   字符串
     * @param begin 开始位置
     * @param end   结束位置
     * @return 截取后的字符串
     */
    public static String substring(String str, int begin, int end) {
        if (str == null) {
            return null;
        }
        if (begin < 0) {
            begin = 0;
        }
        if (end < 0) {
            end = 0;
        }
        if (end > str.length()) {
            end = str.length();
        }
        if (begin > end) {
            return "";
        }
        return str.substring(begin, end);
    }

    /**
     * 转为小写
     *
     * @param str 字符串
     * @return 小写字符串
     */
    public static String toLowerCase(String str) {
        return str == null ? null : str.toLowerCase();
    }

    /**
     * 转为大写
     *
     * @param str 字符串
     * @return 大写字符串
     */
    public static String toUpperCase(String str) {
        return str == null ? null : str.toUpperCase();
    }
} 
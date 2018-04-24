package com.mydemo.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * Cache 工具类
 */
@Slf4j
public class CacheUtils {

    private static CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);

    private static final String SYS_CACHE = "sysCache";

    /**
     * 获取SYS_CACHE缓存
     * @param key
     * @return
     */
    public static Object get(String key){
        return get(SYS_CACHE,key);
    }

    /**
     * 获取SYS_CACHE缓存
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object get(String key,Object defaultValue){
        Object value = get(key);
        return value != null ?value:defaultValue;
    }

    /**
     * 获取缓存
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName,String key){
        return getCache(cacheName).get(key);
    }

    /**
     * 写入SYS_CACHE缓存
     * @param key
     * @return
     */
    public static void put(String key,Object value){
        put(SYS_CACHE,key,value);
    }

    /**
     * 写入缓存
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName,String key,Object value){
        getCache(cacheName).put(key,value);
    }

    /**
     * 从缓存中移除
     * @param cacheName
     * @param key
     */
    public static void remove(String cacheName,String key){
        getCache(cacheName).remove(key);
    }

    /**
     * 获得一个Cache，没有则显示日志。
     * @param cacheName
     * @return
     */
    private static Cache<String,Object> getCache(String cacheName){
        Cache<String,Object> cache = cacheManager.getCache(cacheName);
        if (cache == null){
            throw new RuntimeException("当前系统中没有定义"+cacheName+"这个缓存.");
        }
        return cache;
    }
}
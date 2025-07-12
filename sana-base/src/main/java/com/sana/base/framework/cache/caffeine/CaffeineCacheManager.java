package com.sana.base.framework.cache.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author LON
 * @create 2025/7/12 15:43
 */
@Component
public class CaffeineCacheManager {


    private static final Map<String, Cache<?, ?>> cacheMap = new ConcurrentHashMap<>();

    // 创建缓存
    public static <K, V> Cache<K, V> createCache(String cacheName, long expireAfterWrite, TimeUnit timeUnit, long maximumSize) {
        Caffeine<Object, Object> builder = Caffeine.newBuilder()
                .maximumSize(maximumSize);

        if (expireAfterWrite > 0) {
            builder.expireAfterWrite(expireAfterWrite, timeUnit);
        }

        Cache<K, V> cache = builder.build();
        cacheMap.put(cacheName, cache);
        return cache;
    }

    // 获取缓存
    @SuppressWarnings("unchecked")
    public static <K, V> Cache<K, V> getCache(String cacheName) {
        return (Cache<K, V>) cacheMap.get(cacheName);
    }

    // 设置值
    public static <K, V> void set(String cacheName, K key, V value, long expireAfterWrite, TimeUnit timeUnit) {
        Cache<K, V> cache = getCache(cacheName);
        if (cache == null) {
            cache = createCache(cacheName, expireAfterWrite, timeUnit, 1000);
        }
        cache.put(key, value);
    }

    // 获取值
    public static <K, V> V get(String cacheName, K key) {
        Cache<K, V> cache = getCache(cacheName);
        return cache != null ? cache.getIfPresent(key) : null;
    }

    // 重新设置过期策略（重新创建缓存）
    public static <K, V> void expire(String cacheName, long expireAfterWrite, TimeUnit timeUnit) {
        Cache<K, V> cache = createCache(cacheName, expireAfterWrite, timeUnit, 250);
        cacheMap.put(cacheName, cache);
    }

    // 删除某个键
    public static <K> void deleteKey(String cacheName, K key) {
        Cache<K, ?> cache = getCache(cacheName);
        if (cache != null) {
            cache.invalidate(key);
        }
    }

    // 删除整个缓存
    public static void deleteCache(String cacheName) {
        cacheMap.remove(cacheName);
    }
}

package com.sana.base.syshandle.usercache;

import com.sana.base.cache.redis.CacheKeyBuilder;
import com.sana.base.cache.redis.RedisUtils;
import com.sana.base.syshandle.entity.MyUserDetails;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author LON
 * @create 2025/7/12
 */
@Component
public class UserCacheService {


    @Resource
    private RedisUtils redisUtils;

    /**
     * 将用户信息存储到 Redis（永久缓存）
     */
    public void cacheUserInfo(Long userId, MyUserDetails userDetails) {
        String key = CacheKeyBuilder.userInfoKey(userId);
        redisUtils.set(key, userDetails); // 使用不设置过期时间的方法
    }

    /**
     * 从 Redis 获取用户信息
     */
    public MyUserDetails getUserInfo(Long userId) {
        String key = CacheKeyBuilder.userInfoKey(userId);
        Object obj = redisUtils.get(key);
        return obj instanceof MyUserDetails ? (MyUserDetails) obj : null;
    }

    /**
     * 删除用户信息缓存
     */
    public void removeUserInfo(Long userId) {
        String key = CacheKeyBuilder.userInfoKey(userId);
        redisUtils.delete(key);
    }

    /**
     * 更新用户信息缓存
     */
    public void updateUserInfo(Long userId, MyUserDetails userDetails) {
        cacheUserInfo(userId, userDetails); // 覆盖原有缓存
    }

    /**
     * 检查用户信息是否存在
     */
    public boolean hasUserInfo(Long userId) {
        String key = CacheKeyBuilder.userInfoKey(userId);
        return Boolean.TRUE.equals(redisUtils.hasKey(key));
    }

}

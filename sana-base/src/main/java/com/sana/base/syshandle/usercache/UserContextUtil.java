package com.sana.base.syshandle.usercache;

import cn.dev33.satoken.stp.StpUtil;
import com.sana.base.syshandle.entity.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
*@author LON
*@create 2025/7/24
*/
@Component
public class UserContextUtil {

    private static UserCacheService userCacheService;

    @Autowired
    public void setUserCacheService(UserCacheService userCacheService) {
        UserContextUtil.userCacheService = userCacheService;
    }

    /**
     * 将用户信息存储到 Redis（永久缓存）
     *
     * @param userId 用户ID
     * @param userDetails 用户详情
     */
    public static void cacheUserInfo(Long userId, MyUserDetails userDetails) {
        userCacheService.cacheUserInfo(userId, userDetails);
    }

    /**
     * 从 Redis 获取用户信息
     *
     * @param userId 用户ID
     * @return MyUserDetails 用户详情对象
     */
    public static MyUserDetails getUserInfo(Long userId) {
        return userCacheService.getUserInfo(userId);
    }

    /**
     * 删除用户信息缓存
     *
     * @param userId 用户ID
     */
    public static void removeUserInfo(Long userId) {
        userCacheService.removeUserInfo(userId);
    }

    /**
     * 更新用户信息缓存
     *
     * @param userId 用户ID
     * @param userDetails 用户详情
     */
    public static void updateUserInfo(Long userId, MyUserDetails userDetails) {
        userCacheService.updateUserInfo(userId, userDetails);
    }

    /**
     * 检查用户信息是否存在
     *
     * @param userId 用户ID
     * @return boolean 是否存在
     */
    public static boolean hasUserInfo(Long userId) {
        return userCacheService.hasUserInfo(userId);
    }

    /**
     * 获取当前登录用户详情
     *
     * @return MyUserDetails 用户详情对象
     */
    public static MyUserDetails getCurrentUserInfo() {
        Serializable loginId = (Serializable) StpUtil.getLoginId();
        Long userId = Long.valueOf(loginId.toString());
        return getUserInfo(userId);
    }

    /**
     * 获取当前登录用户ID
     *
     * @return Long 用户ID
     */
    public static Long getCurrentUserId() {
        Serializable loginId = (Serializable) StpUtil.getLoginId();
        return Long.valueOf(loginId.toString());
    }
}

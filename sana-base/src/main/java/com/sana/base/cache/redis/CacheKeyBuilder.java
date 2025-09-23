package com.sana.base.cache.redis;

/**
 * @author LON
 * @create 2025/7/12
 */
public class CacheKeyBuilder {


    /**
     * 用户信息缓存KEY
     */
    public static String userInfoKey(Long key) {

        return CachePrefix.USERINFOKEY + key;
    }

    /**
     * 验证码Key
     */
    public static String captchaKey(String key) {

        return CachePrefix.CAPTCHAKEY + key;
    }

    /**
     * 日志 key
     * @return
     */
    public static String logKey() {

        return CachePrefix.LOGKEY;
    }

    /**
     * 字典 key
     * @return
     */
    public static String dictKey(String key) {

        return CachePrefix.DICTKEY +key;
    }




}

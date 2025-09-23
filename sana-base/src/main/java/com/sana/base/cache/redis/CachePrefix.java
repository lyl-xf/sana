package com.sana.base.cache.redis;

/**
 * @author LON
 * @create 2025/7/12
 */
public interface CachePrefix {


    /**
     * 用户信息缓存
     */
    String USERINFOKEY = "sana:userinfo:";

    /**
     * captcha缓存
     */
    String CAPTCHAKEY = "sana:captcha:";

    /**
     * log缓存
     */
    String LOGKEY = "sana:log";

    /**
     * log缓存
     */
    String DICTKEY = "sana:dict";

}

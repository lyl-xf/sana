package com.sana.base.framework.cache.redis;

/**
 * @author LON
 * @create 2025/7/12
 */
public class CacheKeyBuilder {

    /**
     * 验证码Key
     */
    public static String captchaKey(String key) {

        return CachePrefix.CAPTCHAKEY + key;
    }

}

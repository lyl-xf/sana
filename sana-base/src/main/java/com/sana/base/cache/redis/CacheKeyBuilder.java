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

    /**
     * 设备物模型
     */
    public static String deviceMode(String deviceNub) {

        return CachePrefix.DEVICEMODE+deviceNub;
    }

    /**
     * mqtt json数据缓存
     */
    public static String mqttScript() {

        return CachePrefix.MQTTSCRIPT;
    }

    /**
     * 设备--定时规则关联关系（采集规则）
     */
    public static String deviceIdRuleJon(String deviceNub)
    {

        return CachePrefix.DEVICEIDRULEJOB+deviceNub;
    }

    /**
     * 设备功能
     */
    public static String deviceControl(String deviceNub) {

        return CachePrefix.DEVICECONTROL+deviceNub;
    }

    /**
     * 缓存设备类型
     */
    public static String rulesType(String ruleId) {

        return CachePrefix.RULESTYPE+ruleId;
    }

    /**
     * 这个规则下有多少设备，用于后期的定时采集指令下发（采集规则）
     */
    public static String rulesJobPushDevice(String ruleId) {

        return CachePrefix.RULESJOBPUSHDEVICE+ruleId;
    }

    /**
     * 设备--规则关联关系（监听规则）
     */
    public static String deviceIdRule(String deviceNub) {

        return CachePrefix.DEVICEIDRULE+deviceNub;
    }

    /**
     * 设备--定时规则关联关系（采集规则）
     */
    public static String actionMap(Long actionMapNub) {

        return CachePrefix.ACTIONMAP+actionMapNub;
    }
}

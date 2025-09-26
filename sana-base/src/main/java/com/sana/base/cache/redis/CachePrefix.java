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
    String LOGKEY = "sana:log:";

    /**
     * log缓存
     */
    String DICTKEY = "sana:dict:";

    /**
     * 设备物模型
     */
    String DEVICEMODE = "sana:deviceMode:";

    /**
     * 设备功能，用于在设备定时任务中，定时采集设备的时候，避免频繁地查询数据库。
     */
    String DEVICECONTROL = "sana:deviceControl:";

    /**
     * 设备功能，用于在动作触发的时候，根据设备的功能id进行直接检索对应的动作指令，避免频繁的检索数据库。
     */
    String DEVICEACTIONCOMMANDS = "sana:deviceActionCommands:";

    /**
     * 设备分组，用于分组控制的时候的检索
     */
    String DEVICEGROUP = "sana:deviceGroup:";


    /**
     * 规则类型缓存
     */
    String RULESTYPE = "sana:RulesType:";


    /**
     * 设备--规则关联关系（定时规则）
     */
    String DEVICEIDRULEJOB = "sana:deviceIdRuleJob:";

    /**
     * 脚本缓存
     */
    String MQTTSCRIPT = "sana:mqttScript:";

    /**
     * 设备--规则关联关系（监听规则）
     */
    String DEVICEIDRULE = "sana:deviceIdRule:";


    /**
     * 规则id---设备id关联关系（定时规则）
     */
    String RULESJOBPUSHDEVICE = "sana:rulesJobPushDevice:";

    /**
     * 规则--动作信息
     */
    String ACTIONMAP = "actionMap";

    /**
     * 脚本缓存
     */
    String AVIATORSCRIPT = "sana:aviatorscript:";

    /**
     * 第三方认证缓存，后期作为南向、北向的认证，其实绝大多数情况都会用于北向操作，南向更多的可能会基于mqtt来实现
     */
    String ACCESSTHIRDTOKENKEY = "sana:others:";


}

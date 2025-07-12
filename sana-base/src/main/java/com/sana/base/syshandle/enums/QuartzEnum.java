package com.sana.base.syshandle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据范围枚举
 * @author LON
 * @create 2025/7/12
 */
@Getter
@AllArgsConstructor
public enum QuartzEnum {


    /**
     * 触发 PRE
     */
    TRIGGER_PRE("Trigger_"),


    /**
     * 系统日志触发 PRE
     */
    LOG_TRIGGER_PRE("Trigger_log_"),

    /**
     * 设备数据触发 PRE
     */
    DEVICE_TRIGGER_PRE("Trigger_device_"),

    /**
     * 规则类名称
     */
    RULES_CLASS_NAME("com.sana.rules.executes.job.SchedulerJob"),

    /**
     * 日志类名称
     */
    LOGS_CLASS_NAME("com.sana.system.job.LogOperateJob"),

    /**
     * 设备数据类名称
     */
    DEVICE_CLASS_NAME("com.sana.device.job.DeviceDataJob");


    private final String value;

}
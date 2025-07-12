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
public enum HandleTypeEnum {
    /**
     * 新增
     */
    ADDITIONAL(1),
    /**
     * 修改
     */
    MODIFICATION(2),

    /**
     * 系统定时日志
     */
    SYSTEMLOGS(1),
    /**
     * 设备定时日志
     */
    DEVICELOGS(2);

    private final Integer value;

}
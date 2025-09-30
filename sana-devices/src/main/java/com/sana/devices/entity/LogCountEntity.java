package com.sana.devices.entity;

import lombok.Data;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
public class LogCountEntity {
    private String logType;     // 日志类型（systemLog、deviceActionLog 等）
    private Long countValue;    // 对应日志类型的数量
}

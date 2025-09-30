package com.sana.rules.entity;

import lombok.Data;

/**
 * @author LON
 * @data 2025/9/27
 */
@Data
public class RulesListenDeviceIdEntity {

    private Long id;
    /**
     * 监听任务中的设备id
     */
    private Long deviceId;

    /**
     * 规则id
     */
    private Long ruleId;

}

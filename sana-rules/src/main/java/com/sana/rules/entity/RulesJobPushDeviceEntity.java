package com.sana.rules.entity;

import lombok.Data;

/**
 * @author LON
 * @data 2025/9/27
 */
@Data
public class RulesJobPushDeviceEntity {

    private Long id;
    /**
     * 监听任务中的设备ids,多个id，用，号隔开
     */
    private String deviceIds;

    /**
     * 规则id
     */
    private Long ruleId;

}

package com.sana.rules.entity;

import lombok.Data;

/**
 * @author LON
 * @data 2025/9/27
 */
@Data
public class RulesActionInstancEntity {

    private Long id;
    /**
     * 动作编码
     */
    private String acCode;

    /**
     * 动作内容
     */
    private String acInstancing;
    /**
     * 规则id
     */
    private Long ruleId;
}

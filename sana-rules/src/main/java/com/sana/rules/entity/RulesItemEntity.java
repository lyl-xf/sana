package com.sana.rules.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sana.base.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @data 2025/9/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("rules_item")
public class RulesItemEntity extends BaseEntity {
    /**
     * 规则脚本名称
     */
    private String asName;
    /**
     * 1:脚本，2:表达式
     */
    private Integer asType;

    /**
     * 表达式内容
     */
    private String asContent;

    /**
     * 路径
     */
    private String asPath;

    /**
     * 规则说明
     */
    private String asRemark;

    /**
     * 是否启用
     */
    private Integer asEnable;

    /**
     * 规则类型
     */
    private Integer ruleType;
}

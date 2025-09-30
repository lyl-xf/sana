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
@TableName("rules_item_quratz")
public class RulesItemQuratzEntity extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 所属规则实例id
     */
    private Long rulesId;
    /**
     * cron表达式
     */
    private String cron;

    /**
     * 作业组
     */
    private String jobGroup;

    /**
     * 触发名称
     */
    private String triggerName;

    /**
     * 触发作业组
     */
    private String triggerGroup;

}

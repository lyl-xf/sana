package com.sana.system.entity.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author LON
 * @create 2025/8/30
 */
@Data
@Schema(description = "定时任务维护")
public class JobInfoResult {

    /**
     * 任务名称
     * 可以绑定为规则实例的编码，用于确定唯一性
     */
    @Schema(description = "任务名称")
    private String jobName;

    /**
     * cron表达式
     */
    @Schema(description = "cron表达式")
    private String cron;

    /**
     * 任务组名
     */
    @Schema(description = "任务组名")
    private String jobGroup;

    /**
     * 触发器名称
     */
    @Schema(description = "触发器名称")
    private String triggerName;

    /**
     * 触发器组
     */
    @Schema(description = "触发器组")
    private String triggerGroup;

    /**
     * 任务触发 pre
     */
    private Integer jobType;

}

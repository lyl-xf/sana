package com.sana.rules.entity.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author LON
 * @data 2025/9/27
 */
@Data
@Schema(description = "规则节点")
public class RulesItemNodeResult {

    /**
     * 所属规则实例id
     */
    @Schema(description = "所属规则实例id")
    private Long rulesId;

    /**
     * 实例名称
     */
    @Schema(description = "实例名称")
    private String name;

    /**
     * 节点设置
     */
    @Schema(description = "节点设置")
    private String nodeConfig;
}

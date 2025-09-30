package com.sana.rules.entity.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author LON
 * @data 2025/9/27
 */
@Data
@Schema(description = "规则节点实体")
public class RulesItemNodeQuery {

    /**
     * 规则实例id
     */
    @NotNull(message = "规则实例id")
    @Schema(description = "rulesId")
    private Long rulesId;

}

package com.sana.rules.entity.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author LON
 * @data 2025/9/27
 */
@Data
@Schema(description = "规则查询实体")
public class RulesItemQuery{

    /**
     * 设备id
     */
    @NotNull(message = "设备id不能为空")
    @Schema(description = "设备id")
    private Long deviceId;

}

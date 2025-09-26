package com.sana.abutment.entity.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author LON
 * @create 2025/9/21
 */
@Data
@Schema(description = "情景模式mqtt协议")
public class RuleProtocolsMqttResult {

    @Schema(description = "id")
    private Long id;
    /**
     * mqtt名称
     */
    private String label;

}

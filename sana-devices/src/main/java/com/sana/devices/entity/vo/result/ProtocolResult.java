package com.sana.devices.entity.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
@Schema(description = "mqtt协议信息")
public class ProtocolResult {

    @Schema(description = "id")
    private Long value;

    @Schema(description = "mqtt协议名称")
    private String label;

}

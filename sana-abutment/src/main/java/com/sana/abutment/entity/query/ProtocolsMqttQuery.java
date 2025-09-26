package com.sana.abutment.entity.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @create 2025/9/21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "协议名称")
public class ProtocolsMqttQuery {

    /**
     * 协议名称
     */
    @Schema(description = "协议名称")
    private String name;

}
package com.sana.devices.entity.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "设备功能查询")
public class DeviceControlDataQuery {

    @Schema(description = "设备id")
    private Long deviceItemId;
}

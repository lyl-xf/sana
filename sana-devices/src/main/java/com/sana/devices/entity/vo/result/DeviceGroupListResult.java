package com.sana.devices.entity.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
@Schema(description = "设备分组规则列表查询")
public class DeviceGroupListResult {

    @Schema(description = "id")
    private Long id;

    /**
     * 设备分组
     */
    @Schema(description = "设备分组名称")
    private String label;

}

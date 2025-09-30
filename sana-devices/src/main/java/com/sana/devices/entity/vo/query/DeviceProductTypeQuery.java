package com.sana.devices.entity.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
@Schema(description = "产品类型")
public class DeviceProductTypeQuery{

    /**
     * 机构名称
     */
    @Schema(description = "产品名称")
    private String label;

}

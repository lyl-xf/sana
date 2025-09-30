package com.sana.devices.entity.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
@Schema(description = "边缘计算选项")
public class EdgesItemDataResult {

    @Schema(description = "id")
    private Long value;

    @Schema(description = "边缘计算设备名称")
    private String label;

}

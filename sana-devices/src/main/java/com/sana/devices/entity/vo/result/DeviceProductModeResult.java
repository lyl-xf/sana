package com.sana.devices.entity.vo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
@Schema(description = "产品物模型")
public class DeviceProductModeResult {

    @Schema(description = "id")
    private Long id;
    /**
     * 属性名称
     */
    @Schema(description = "属性名称")
    private String modeName;
    /**
     * 属性标志
     */
    @Schema(description = "属性标志")
    private String modeSigns;

    /**
     * 数据类型
     */
    @Schema(description = "数据类型")
    private String modeDataType;

    /**
     * 补充说明
     */
    @Schema(description = "补充说明")
    private String modeRemark;
    /**
     * 产品实例id
     */
    @Schema(description = "产品实例id")
    private Long productTypeId;
}

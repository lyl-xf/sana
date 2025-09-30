package com.sana.devices.entity.vo.save;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
@Schema(description = "设备分组")
public class DeviceGroupSave {


    @Schema(description = "id")
    private Long id;

    /**
     * 设备分组
     */
    @Schema(description = "设备分组")
    private String name;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

}

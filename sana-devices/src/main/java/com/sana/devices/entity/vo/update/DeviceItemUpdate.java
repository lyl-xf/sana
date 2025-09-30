package com.sana.devices.entity.vo.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
@Schema(description = "产品类型")
public class DeviceItemUpdate {


    @Schema(description = "id")
    private Long id;
    /**
     * 设备图片
     */
    @Schema(description = "设备图片")
    private String imgs;
    /**
     * 设备名称
     */
    @Schema(description = "设备名称")
    @NotBlank(message = "设备名称不能为空")
    private String name;
    /**
     * 设备编码
     */
    @Schema(description = "设备编码")
    private String deviceNumber;

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

    /**
     * 产品类型id
     */
    @Schema(description = "产品类型id")
    private Long productTypeId;

    /**
     * 产品编辑回显内容
     */
    @Schema(description = "产品编辑回显内容")
    private List<Long> productTypeShow;
}

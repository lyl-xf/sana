package com.sana.devices.entity.vo.save;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
@Schema(description = "产品类型")
public class DeviceProductTypeSave{
    /**
     * 上级ID
     */
    @Schema(description = "上级ID")
    private Long pid;
    /**
     * 机构名称
     */
    @Schema(description = "机构名称")
    private String label;
    /**
     * 排序
     */
    @Schema(description = "排序")
    @Min(value = 0, message = "排序值不能小于0")
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

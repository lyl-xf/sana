package com.sana.system.entity.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @auther LON
 */

@Data
@Schema(description = "机构")
public class SysOrgUpdate {

    @Schema(description = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "上级ID", required = true)
    private Long pid;

    @Schema(description = "机构名称", required = true)
    @NotBlank(message = "机构名称不能为空")
    private String name;

    @Schema(description = "排序", required = true)
    @Min(value = 0, message = "排序值不能小于0")
    private Integer sort;

    @Schema(description = "是否启用", required = true)
    private Integer status;

    @Schema(description = "备注")
    private String remark;


}

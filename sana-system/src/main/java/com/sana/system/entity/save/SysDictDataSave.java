package com.sana.system.entity.save;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LON
 * @create 2025/9/21 14:41
 */
@Data
@Schema(description = "保存字典数据")
public class SysDictDataSave implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "字典类型ID", required = true)
    @NotNull(message = "字典类型ID不能为空")
    private Long dic;

    @Schema(description = "字典标签", required = true)
    @NotBlank(message = "字典标签不能为空")
    private String name;

    @Schema(description = "字典值")
    private String key;

    @Schema(description = "有效")
    private String yx;

    @Schema(description = "排序", required = true)
    @Min(value = 0, message = "排序值不能小于0")
    private Integer sort;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;
}
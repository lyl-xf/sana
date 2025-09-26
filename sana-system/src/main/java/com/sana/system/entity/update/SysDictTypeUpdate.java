package com.sana.system.entity.update;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sana.base.utils.DateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LON
 * @create 2025/9/21 15:39
 */
@Data
@Schema(description = "修改字典类型")
public class SysDictTypeUpdate implements Serializable {
    private static final long serialVersionUID = 1L;


    @Schema(description = "id")
    private Long id;

    @Schema(description = "字典编码", required = true)
    @NotBlank(message = "字典类型不能为空")
    private String code;

    @Schema(description = "字典名称", required = true)
    @NotBlank(message = "字典名称不能为空")
    private String name;


    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private Date updateTime;
}

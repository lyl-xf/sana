package com.sana.system.entity.query;

import com.sana.base.syshandle.page.SanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @create 2025/9/1 14:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "字典数据查询")
public class SysDictDataQuery extends SanaPageParams {


    @Schema(description = "字典code", required = true)
    @NotNull(message = "字典code不能为空")
    private String code;

}
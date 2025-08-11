package com.sana.system.entity.query;

import com.sana.base.syshandle.page.SanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @auther LON
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "操作日志查询")
public class SysLogOperateQuery extends SanaPageParams {
    @Schema(description = "用户")
    private Long userId;



}

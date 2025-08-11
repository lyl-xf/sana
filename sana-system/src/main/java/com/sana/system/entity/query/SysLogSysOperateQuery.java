package com.sana.system.entity.query;

import com.sana.base.syshandle.page.SanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @create 2025/8/10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "系统操作日志查询")
public class SysLogSysOperateQuery extends SanaPageParams {

    @Schema(description = "日志类型")
    private Integer operateType;

    @Schema(description = "开始时间")
    private String startTime;

    @Schema(description = "结束时间")
    private String endTime;

}

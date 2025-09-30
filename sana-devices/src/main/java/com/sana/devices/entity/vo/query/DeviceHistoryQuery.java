package com.sana.devices.entity.vo.query;

import com.sana.base.syshandle.page.SanaPageParams;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "设备历史数据查询实体")
public class DeviceHistoryQuery extends SanaPageParams {

    @NotNull(message = "设备ID不能为空")
    @Schema(description = "设备ID")
    private Long deviceId;


    @NotNull(message = "开始时间不能为空")
    @Schema(description = "开始时间")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;

    @NotNull(message = "开始时间不能为空")
    @Schema(description = "结束时间")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;


}

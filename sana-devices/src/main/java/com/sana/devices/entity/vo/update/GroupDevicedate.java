package com.sana.devices.entity.vo.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
@Schema(description = "保存绑定分组设备")
public class GroupDevicedate {


    @Schema(description = "所绑定的分组id")
    private Long groupId;


    @Schema(description = "所选择的设备id")
    private Long deviceId;
}

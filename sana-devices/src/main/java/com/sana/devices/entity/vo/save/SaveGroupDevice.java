package com.sana.devices.entity.vo.save;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
@Schema(description = "保存绑定分组设备")
public class SaveGroupDevice {


    @Schema(description = "所绑定的分组数据")
    private Long groupId;


    @Schema(description = "所选择的设备ids")
    private List<Long> deviceIds;
}

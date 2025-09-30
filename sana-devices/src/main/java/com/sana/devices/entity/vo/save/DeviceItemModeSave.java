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
@Schema(description = "设备物模型")
public class DeviceItemModeSave {

    /**
     * 产品物模型实例
     */
    @Schema(description = "设备物模型实例")
    private List<DeviceModeListSave> deviceModeListSave;
    /**
     * 设备实例id
     */
    @Schema(description = "设备实例id")
    private Long deviceItemId;

}

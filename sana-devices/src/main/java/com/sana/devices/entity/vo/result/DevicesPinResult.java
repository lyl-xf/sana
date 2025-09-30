package com.sana.devices.entity.vo.result;

import lombok.Data;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Data
public class DevicesPinResult {

    private int value;
    private String name;

    public DevicesPinResult(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
}

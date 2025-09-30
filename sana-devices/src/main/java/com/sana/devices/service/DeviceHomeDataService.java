package com.sana.devices.service;

import com.sana.devices.entity.vo.result.DevicesPinResult;
import com.sana.devices.entity.vo.result.LogCountResult;

import java.util.List;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
public interface DeviceHomeDataService {


    List<DevicesPinResult> getDeviceStatusList();

    LogCountResult getLogData();
}

package com.sana.devices.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.devices.entity.vo.result.DevicesPinResult;
import com.sana.devices.entity.vo.result.LogCountResult;
import com.sana.devices.service.DeviceHomeDataService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@RestController
@RequestMapping("/devices/deviceHomeData")
@Tag(name = "设备列在线情况查询")
@ApiSupport(author = "LON")
public class DeviceHomeDataController {

    @Resource
    private DeviceHomeDataService deviceHomeDataService;

    @GetMapping("/devicesPin")
    public SanaResult<List<DevicesPinResult>> devicesPin() {
        return SanaResult.ok(deviceHomeDataService.getDeviceStatusList());
    }


    @GetMapping("/getLogData")
    public SanaResult<LogCountResult> getLogData() {
        return SanaResult.ok(deviceHomeDataService.getLogData());
    }


}

package com.sana.devices.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.devices.entity.vo.result.ColumnResult;
import com.sana.devices.entity.vo.result.DeviceModeResult;
import com.sana.devices.entity.vo.save.DeviceItemModeSave;
import com.sana.devices.entity.vo.update.DeviceModeUpdate;
import com.sana.devices.service.DeviceModeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@RestController
@RequestMapping("/devices/deviceMode")
@Tag(name = "设备物模型")
@ApiSupport(author = "LON")
public class DeviceModeController {

    @Resource
    private DeviceModeService deviceModeService;

    @GetMapping("/list")
    @Operation(summary = "列表查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<List<DeviceModeResult>> getDeviceItemModeList(@RequestParam("id") Long id) {
        List<DeviceModeResult> sanaList = deviceModeService.getDeviceItemModeList(id);
        return SanaResult.ok(sanaList);
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    public SanaResult saveDeviceModes(@RequestBody @Valid DeviceItemModeSave saveVO) {
        deviceModeService.saveDeviceModes(saveVO);
        return SanaResult.ok();
    }
    @GetMapping("/contoleList")
    @Operation(summary = "控制属性列表查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<List<DeviceModeResult>> getDeviceContoleList(@RequestParam("id") Long id) {
        List<DeviceModeResult> sanaList = deviceModeService.getDeviceContoleList(id);
        return SanaResult.ok(sanaList);
    }

    @PostMapping("/saveContoleModel")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult saveContoleModel(@RequestBody @Valid DeviceModeUpdate updataVO) {
        deviceModeService.saveContoleModel(updataVO);
        return SanaResult.ok();
    }

    @GetMapping("/getDeviceModeMap")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult<List<ColumnResult>> getDeviceModeMap(@RequestParam("deviceId") Long deviceId) {
        return SanaResult.ok(deviceModeService.getDeviceModeMap(deviceId));
    }



}

package com.sana.devices.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;

import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.devices.entity.DeviceControlDataEntity;
import com.sana.devices.entity.vo.query.DeviceControlDataQuery;
import com.sana.devices.entity.vo.result.DeviceControlDataResult;
import com.sana.devices.entity.vo.save.DeviceControlDataSave;
import com.sana.devices.service.DeviceControlDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@RestController
@RequestMapping("/devices/deviceControlData")
@Tag(name = "设备功能")
@ApiSupport(author = "LON")
public class DeviceControlDataController {

    @Resource
    private DeviceControlDataService deviceControlDataService;


    @GetMapping("/list")
    @Operation(summary = "设备功能-列表")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<List<DeviceControlDataResult>> list(@ParameterObject @Valid DeviceControlDataQuery query) {
        List<DeviceControlDataResult> list = deviceControlDataService.getDeviceControlDataList(query);
        return SanaResult.ok(list);
    }


    /**
     * 新增
     */
    @PostMapping("/saveOrUpdate")
    @Operation(summary = "设备功能-保存/修改")
    @OptLog(type = OperateTypeEnum.INSERT)
    public SanaResult<DeviceControlDataEntity> save(@RequestBody @Valid DeviceControlDataSave saveVO) {
        return SanaResult.ok(deviceControlDataService.saveDeviceControl(saveVO));
    }

    /**
     * 修改
     */
/*    @PostMapping("/update")
    @Operation(summary = "暂时不用，自己写前端，双向迁就....")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public LanaResult update(@RequestBody @Valid DeviceControlDataUpdate updateVO) {
        deviceControlDataService.updateDeviceControlData(updateVO);
        return LanaResult.ok();
    }*/
    /**
     * 删除
     */
    @GetMapping("/delete")
    @Operation(summary = "设备功能-删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    public SanaResult delete(@RequestParam("id") Long id) {
        deviceControlDataService.deleteDeviceControlData(id);
        return SanaResult.ok();
    }
}

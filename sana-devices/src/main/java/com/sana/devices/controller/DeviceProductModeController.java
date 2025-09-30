package com.sana.devices.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.devices.entity.vo.result.DeviceProductModeResult;
import com.sana.devices.entity.vo.save.DeviceProductModeSave;
import com.sana.devices.service.DeviceProductModeService;
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
@RequestMapping("/devices/deviceProductMode")
@Tag(name = "设备产品物模型")
@ApiSupport(author = "LON")
public class DeviceProductModeController {
    @Resource
    private DeviceProductModeService deviceProductModeService;


    @GetMapping("/list")
    @Operation(summary = "列表查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<List<DeviceProductModeResult>> getProductModePage(@RequestParam("id") Long id) {
        List<DeviceProductModeResult> lanaPage = deviceProductModeService.getProductModePage(id);
        return SanaResult.ok(lanaPage);
    }



    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    public SanaResult saveProductModePage(@RequestBody @Valid DeviceProductModeSave saveVO) {
        deviceProductModeService.saveDeviceGroup(saveVO);
        return SanaResult.ok();
    }


}

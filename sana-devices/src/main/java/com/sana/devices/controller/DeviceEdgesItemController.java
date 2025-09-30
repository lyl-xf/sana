package com.sana.devices.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;

import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.devices.entity.DeviceEdgesItemEntity;
import com.sana.devices.entity.vo.query.DeviceEdgesQuery;
import com.sana.devices.entity.vo.result.DeviceEdgesItemResult;
import com.sana.devices.entity.vo.save.DeviceEdgesItemSave;
import com.sana.devices.entity.vo.update.DeviceEdgesItemUpdate;
import com.sana.devices.service.DeviceEdgesItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@RestController
@RequestMapping("/devices/edgesItem")
@Tag(name = "边缘计算管理")
@ApiSupport(author = "LON")
public class DeviceEdgesItemController {

    @Resource
    private DeviceEdgesItemService deviceEdgesItemService;


    /**
     * 数据分页查询
     * @param
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "查询边缘实例数据")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<SanaPage<DeviceEdgesItemResult>> getDeviceEdgesItemPage(@ParameterObject @Valid DeviceEdgesQuery deviceEdgesQuery) {
        SanaPage<DeviceEdgesItemResult> DeviceEdgesItemResultPage = deviceEdgesItemService.getDeviceEdgesItemPage(deviceEdgesQuery);
        return SanaResult.ok(DeviceEdgesItemResultPage);
    }


    /**
     * 保存边缘实例
     * 注意，设备产品详情中也使用了该接口
     * @param
     * @return
     */
    @PostMapping("/save")
    @Operation(summary = "保存边缘实例")
    @OptLog(type = OperateTypeEnum.INSERT)
    public SanaResult<DeviceEdgesItemEntity> saveDeviceEdgesItem(@RequestBody @Valid DeviceEdgesItemSave saveVo) {
        return SanaResult.ok(deviceEdgesItemService.saveDeviceEdgesItem(saveVo));
    }


    /**
     * 修改边缘实例
     * 注意，设备产品详情中也使用了该接口
     * @param
     * @return
     */
    @PostMapping("/update")
    @Operation(summary = "修改边缘实例")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult<DeviceEdgesItemEntity> updateDeviceEdgesItem(@RequestBody @Valid DeviceEdgesItemUpdate updateVo) {
        return SanaResult.ok(deviceEdgesItemService.updateDeviceEdgesItem(updateVo));
    }


}

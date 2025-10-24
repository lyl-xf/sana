package com.sana.devices.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.devices.entity.vo.query.DeviceProductTypeQuery;
import com.sana.devices.entity.vo.result.DeviceAbutmentResult;
import com.sana.devices.entity.vo.result.DeviceProductTypeResult;
import com.sana.devices.entity.vo.save.DeviceProductTypeSave;
import com.sana.devices.entity.vo.save.SaveDeviceAbutmentSave;
import com.sana.devices.entity.vo.update.DeviceProductTypeUpdate;
import com.sana.devices.service.DeviceProductTypeService;
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
@RequestMapping("/devices/deviceProductType")
@Tag(name = "产品分类")
@ApiSupport(author = "LON")
public class DeviceProductTypeController {

    @Resource
    private DeviceProductTypeService deviceProductTypeService;
    @GetMapping("/list")
    @Operation(summary = "列表")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@SaCheckPermission("devices:deviceProductType:list")
    public SanaResult<List<DeviceProductTypeResult>> list(@ParameterObject @Valid DeviceProductTypeQuery query) {
        List<DeviceProductTypeResult> list = deviceProductTypeService.getList(query);

        return SanaResult.ok(list);
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    public SanaResult<String> save(@RequestBody @Valid DeviceProductTypeSave saveVO) {
        deviceProductTypeService.saveDeviceProductType(saveVO);
        return SanaResult.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult<String> update(@RequestBody @Valid DeviceProductTypeUpdate updateVO) {
        deviceProductTypeService.updateDeviceProductType(updateVO);

        return SanaResult.ok();
    }

    @GetMapping("/delete")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    public SanaResult<String> delete(@RequestParam("id") Long id) {
        deviceProductTypeService.deleteDeviceProductType(id);

        return SanaResult.ok();
    }

    /**
     * 产品接入管理配置
     */
    @GetMapping("/getDeviceProtocolsMode")
    @Operation(summary = "查询所属协议")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<DeviceAbutmentResult> getDeviceProtocolsMode(@RequestParam("id") Long id) {
        DeviceAbutmentResult data = deviceProductTypeService.getDeviceProtocolsMode(id);
        return SanaResult.ok(data);
    }

    @PostMapping("/saveDeviceProtocolsMode")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    public SanaResult saveDeviceProtocolsMode(@RequestBody @Valid SaveDeviceAbutmentSave saveVO) {
        //作为产品与接入配置的关联关系表，先增在删
        deviceProductTypeService.saveDeviceProtocolsMode(saveVO);
        return SanaResult.ok();
    }

}

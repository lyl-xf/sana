package com.sana.devices.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.devices.entity.vo.query.DeviceGroupQuery;
import com.sana.devices.entity.vo.query.GroupDeviceItemQuery;
import com.sana.devices.entity.vo.result.DeviceGroupListResult;
import com.sana.devices.entity.vo.result.DeviceGroupResult;
import com.sana.devices.entity.vo.result.GroupDeviceItemResult;
import com.sana.devices.entity.vo.save.DeviceGroupSave;
import com.sana.devices.entity.vo.save.SaveGroupDevice;
import com.sana.devices.entity.vo.update.DeviceGroupUpdate;
import com.sana.devices.entity.vo.update.GroupDevicedate;
import com.sana.devices.service.DeviceGroupService;
import com.sana.devices.service.DeviceItemService;
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
@RequestMapping("/devices/deviceGroup")
@Tag(name = "设备分组")
@ApiSupport(author = "LON")
public class DeviceGroupController {
    @Resource
    private DeviceGroupService deviceGroupService;
    @Resource
    private DeviceItemService deviceItemService;


    @GetMapping("/page")
    @Operation(summary = "分页查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<SanaPage<DeviceGroupResult>> getDeviceGroupPage(@ParameterObject @Valid DeviceGroupQuery query) {
        SanaPage<DeviceGroupResult> lanaPage = deviceGroupService.getDeviceGroupPage(query);
        return SanaResult.ok(lanaPage);
    }


    @GetMapping("/getDeviceGroupList")
    @Operation(summary = "分组列表查询，规则编排使用")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<List<DeviceGroupListResult>> getDeviceGroupList() {
        List<DeviceGroupListResult> deviceGroupList = deviceGroupService.getDeviceGroupList();
        return SanaResult.ok(deviceGroupList);
    }

    /**
     * 用户数据分页查询
     * @param query
     * @return
     */
    @GetMapping("/groupDeviceItemPage")
    @Operation(summary = "所属分组设备分页查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<SanaPage<GroupDeviceItemResult>> groupDeviceItemPage(@ParameterObject @Valid GroupDeviceItemQuery query) {
        SanaPage<GroupDeviceItemResult> lanaPage = deviceItemService.groupDeviceItemPage(query);
        return SanaResult.ok(lanaPage);
    }

    @GetMapping("/groupDeviceItemList")
    @Operation(summary = "所属分组查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<List<GroupDeviceItemResult>> groupDeviceItemList(@RequestParam  Long groupId ,@RequestParam String deviceName) {
        List<GroupDeviceItemResult> List = deviceItemService.groupDeviceItemList(groupId,deviceName);
        return SanaResult.ok(List);
    }

    @PostMapping("/saveBindingDevice")
    @Operation(summary = "保存绑定的设备")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult<String> saveBindingDevice(@RequestBody @Valid SaveGroupDevice saveVO) {
        deviceGroupService.saveBindingDevice(saveVO);
        return SanaResult.ok();
    }

    @PostMapping("/deleteBindingDevice")
    @Operation(summary = "解除绑定的设备")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult<String> deleteBindingDevice(@RequestBody @Valid GroupDevicedate updateVo) {
        deviceGroupService.deleteBindingDevice(updateVo);
        return SanaResult.ok();
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    public SanaResult saveDeviceGroup(@RequestBody @Valid DeviceGroupSave saveVO) {
        deviceGroupService.saveDeviceGroup(saveVO);
        return SanaResult.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult<String> updateDeviceGroup(@RequestBody @Valid DeviceGroupUpdate updateVo) {
        deviceGroupService.updateDeviceGroup(updateVo);
        return SanaResult.ok();
    }

    @GetMapping("/delete")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    public SanaResult<String> deleteDeviceGroup(@RequestParam("id") List<Long> idList) {
        deviceGroupService.deleteDeviceGroup(idList);
        return SanaResult.ok();
    }

}

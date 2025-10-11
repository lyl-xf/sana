package com.sana.devices.controller;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.devices.entity.vo.query.DeviceHistoryQuery;
import com.sana.devices.entity.vo.query.DeviceItemQuery;
import com.sana.devices.entity.vo.result.DeviceItemResult;
import com.sana.devices.entity.vo.save.DeviceItemSave;
import com.sana.devices.entity.vo.update.DeviceItemUpdate;
import com.sana.devices.service.DeviceItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@RestController
@RequestMapping("/devices/deviceItem")
@Tag(name = "设备管理")
@ApiSupport(author = "LON")
public class DeviceItemController {
    @Resource
    private DeviceItemService deviceItemService;

    /**
     * 数据分页查询
     * @param query
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<SanaPage<DeviceItemResult>> getDeviceItemPage(@ParameterObject @Valid DeviceItemQuery query) {
        SanaPage<DeviceItemResult> sanaPage = deviceItemService.getDeviceItemPage(query);
        return SanaResult.ok(sanaPage);
    }




    // todo 项目启动的时候，需要将物模型的数据初始化在redis中

    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    public SanaResult saveDeviceItem(@RequestBody @Valid DeviceItemSave vo) {
        deviceItemService.saveDeviceItem(vo);
        return SanaResult.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult update(@RequestBody @Valid DeviceItemUpdate vo) {
        deviceItemService.updateByUserId(vo);
        return SanaResult.ok();
    }


    @PostMapping("/delete")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    public SanaResult<String> delete(@RequestBody List<Long> idList) {
        deviceItemService.deleteDeviceItem(idList);
        return SanaResult.ok();
    }

    @GetMapping("/historyData")
    @Operation(summary = "历史数据查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<SanaPage<Map<String, Object>>> historyData(@ParameterObject @Valid DeviceHistoryQuery query) {
        SanaPage<Map<String, Object>> sanaPage = deviceItemService.historyData(query);
        return SanaResult.ok(sanaPage);
    }
}

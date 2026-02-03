package com.sana.abutment.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.abutment.entity.query.ProtocolsMqttQuery;
import com.sana.abutment.entity.result.ProtocolsMqttResult;
import com.sana.abutment.entity.result.RuleProtocolsMqttResult;
import com.sana.abutment.entity.save.ProtocolsMqttSave;
import com.sana.abutment.entity.update.ProtocolsMqttUpdate;
import com.sana.abutment.initializer.proties.mqttclient.ProtocolsMqttClientProperties;
import com.sana.abutment.service.ProtocolsMqttService;
import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.result.SanaResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author LON
 * @create 2025/9/21
 */
@RestController
@RequestMapping("/abutment/mqtt")
@Tag(name = "mqtt协议")
@ApiSupport(author = "LON")
public class ProtocolsMqttController {


    @Resource
    private ProtocolsMqttService protocolsMqttService;
    @GetMapping("/page")
    @Operation(summary = "查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@SaCheckPermission("protocols:mqtt:page)")
    public SanaResult<List<ProtocolsMqttResult>> getMqttGroupPage(@ParameterObject @Valid ProtocolsMqttQuery query) {
        List<ProtocolsMqttResult> sanaPage = protocolsMqttService.getMqttGroupPage(query);
        return SanaResult.ok(sanaPage);
    }



    @GetMapping("/getMqttProxyClientMessage")
    @Operation(summary = "查询mqtt客户端代理")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<ProtocolsMqttClientProperties> getMqttProxyClientMessage() {
        return SanaResult.ok(protocolsMqttService.getProtocolsMqttClientProperties());
    }

    @GetMapping("/getMqttBroker")
    @Operation(summary = "获取mqtt信息")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<ProtocolsMqttResult> getMqttBroker(@RequestParam String deviceId) {

        ProtocolsMqttResult sanaPage = protocolsMqttService.getMqttBroker(deviceId);
        return SanaResult.ok(sanaPage);
    }

    @GetMapping("/list")
    @Operation(summary = "情景联动协议查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    //@SaCheckPermission("hasAuthority('protocols:mqtt:page')")
    public SanaResult<List<RuleProtocolsMqttResult>> getMqttGroupList(@ParameterObject @Valid ProtocolsMqttQuery query) {
        List<RuleProtocolsMqttResult> sanaPage = protocolsMqttService.getMqttGroupList(query);
        return SanaResult.ok(sanaPage);
    }

    @PostMapping("/save")
    @Operation(summary = "保存")
    @OptLog(type = OperateTypeEnum.INSERT)
    public SanaResult saveProtocolsMqttSave(@RequestBody @Valid ProtocolsMqttSave saveVO) {
        protocolsMqttService.saveProtocolsMqttSave(saveVO);
        return SanaResult.ok();
    }

    @PostMapping("/update")
    @Operation(summary = "修改")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult<String> updateProtocolsMqtt(@RequestBody @Valid ProtocolsMqttUpdate updateVo) {
        protocolsMqttService.updateProtocolsMqtt(updateVo);
        return SanaResult.ok();
    }

    @GetMapping("/delete")
    @Operation(summary = "删除")
    @OptLog(type = OperateTypeEnum.DELETE)
    //@SaCheckPermission("abutment:protocols:del")
    public SanaResult<String> deleteProtocolsMqtt(@RequestParam("id") Long id) {
        protocolsMqttService.deleteProtocolsMqtt(id);
        return SanaResult.ok();
    }

}

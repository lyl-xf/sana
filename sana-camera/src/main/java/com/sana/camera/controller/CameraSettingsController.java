package com.sana.camera.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.camera.entity.CameraStreamProxyEntity;
import com.sana.camera.entity.query.CameraStreamProxyQuery;
import com.sana.camera.entity.query.ZLMediaKitQuery;
import com.sana.camera.entity.result.CameraStreamProxyResult;
import com.sana.camera.entity.update.ServerConfigUpdate;
import com.sana.camera.proties.ZLMediaKit;
import com.sana.camera.service.CameraSettingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

/**
 * @author LON
 * @create 2025/11/4 16:36
 */
@RestController
@RequestMapping("/camera/settings")
@Tag(name = "设备功能")
@ApiSupport(author = "LON")
public class CameraSettingsController {

    @Resource
    private ZLMediaKit zLMediaKit;
    @Resource
    private CameraSettingsService cameraSettingsService;


    /**
     * 数据分页查询
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<SanaPage<CameraStreamProxyResult>> getStreamProxyPage(@ParameterObject @Valid CameraStreamProxyQuery query) {
        SanaPage<CameraStreamProxyResult> sanaPage = cameraSettingsService.getStreamProxyPage(query);
        return SanaResult.ok(sanaPage);
    }


    @GetMapping("/getZLMSetting")
    @Operation(summary = "流媒体节点列表")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<ZLMediaKit> list() {

        ZLMediaKit zLMediaKit = cameraSettingsService.getZLMediaKit();

        return SanaResult.ok(zLMediaKit);
    }

    @PostMapping("/getZLMServerConfig")
    @Operation(summary = "获取ZLM服务器配置")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult<JSONObject> getZLMServerConfig(@RequestBody ZLMediaKitQuery query) {
        return cameraSettingsService.getZLMServerConfig(query);
    }


    @PostMapping("/updateZLMServerConfig")
    @Operation(summary = "修改ZLM服务器配置")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult updateZLMServerConfig(@RequestBody ServerConfigUpdate jsonOject) {
        return cameraSettingsService.updateZLMServerConfig(jsonOject);
    }


    @PostMapping("/restartServer")
    @Operation(summary = "重启ZLM服务器")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult restartServer(@RequestBody ZLMediaKitQuery jsonOject) {
        return cameraSettingsService.restartServer(jsonOject);
    }



    @PostMapping("/addStreamProxy")
    @Operation(summary = "新增拉流代理")
    @OptLog(type = OperateTypeEnum.INSERT)
    public SanaResult addStreamProxy(@RequestBody CameraStreamProxyEntity cameraStreamProxy) {
        return cameraSettingsService.addStreamProxy(cameraStreamProxy);
    }


    @PostMapping("/updateStreamProxy")
    @Operation(summary = "修改拉流代理")
    @OptLog(type = OperateTypeEnum.UPDATE)
    public SanaResult updateStreamProxy(@RequestBody CameraStreamProxyEntity cameraStreamProxy) {
        return cameraSettingsService.updateStreamProxy(cameraStreamProxy);
    }


    @GetMapping("/delStreamProxy")
    @Operation(summary = "删除代理节点")
    @OptLog(type = OperateTypeEnum.DELETE)
    public SanaResult<ZLMediaKit> delStreamProxy(@RequestParam String id ) {

        return cameraSettingsService.delStreamProxy(id);

    }

}

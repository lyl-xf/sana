package com.sana.camera.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.sana.base.syshandle.enums.OperateTypeEnum;
import com.sana.base.syshandle.operatelog.OptLog;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.camera.entity.query.ZLMediaKitQuery;
import com.sana.camera.entity.update.ServerConfigUpdate;
import com.sana.camera.proties.ZLMediaKit;
import com.sana.camera.service.CameraSettingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
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

        JSONObject zLMediaKitConfig = cameraSettingsService.getZLMServerConfig(query);

        return SanaResult.ok(zLMediaKitConfig);
    }


    @PostMapping("/updateZLMServerConfig")
    @Operation(summary = "获取ZLM服务器配置")
    @OptLog(type = OperateTypeEnum.QUERY)
    public SanaResult updateZLMServerConfig(@RequestBody ServerConfigUpdate jsonOject) {

        return cameraSettingsService.updateZLMServerConfig(jsonOject);
    }


}

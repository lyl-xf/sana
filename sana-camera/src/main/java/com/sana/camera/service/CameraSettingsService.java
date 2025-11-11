package com.sana.camera.service;

import com.alibaba.fastjson.JSONObject;
import com.sana.base.mybatis.service.BaseService;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.camera.entity.CameraStreamProxyEntity;
import com.sana.camera.entity.query.CameraStreamProxyQuery;
import com.sana.camera.entity.query.ZLMediaKitQuery;
import com.sana.camera.entity.result.CameraStreamProxyResult;
import com.sana.camera.entity.update.ServerConfigUpdate;
import com.sana.camera.proties.ZLMediaKit;
import jakarta.validation.Valid;

/**
 * @author LON
 * @create 2025/11/4 17:01
 */
public interface CameraSettingsService extends BaseService<CameraStreamProxyEntity> {

    ZLMediaKit getZLMediaKit();

    SanaResult<JSONObject> getZLMServerConfig(ZLMediaKitQuery query);

    SanaResult updateZLMServerConfig(ServerConfigUpdate jsonOject);

    SanaResult restartServer(ZLMediaKitQuery jsonOject);

    SanaResult addStreamProxy(CameraStreamProxyEntity cameraStreamProxy);

    SanaPage<CameraStreamProxyResult> getStreamProxyPage(@Valid CameraStreamProxyQuery query);

    SanaResult updateStreamProxy(CameraStreamProxyEntity cameraStreamProxy);

    SanaResult<ZLMediaKit> delStreamProxy(String id);
}

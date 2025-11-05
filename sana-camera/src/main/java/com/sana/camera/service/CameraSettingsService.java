package com.sana.camera.service;

import com.alibaba.fastjson.JSONObject;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.camera.entity.query.ZLMediaKitQuery;
import com.sana.camera.entity.update.ServerConfigUpdate;
import com.sana.camera.proties.ZLMediaKit;

/**
 * @author LON
 * @create 2025/11/4 17:01
 */
public interface CameraSettingsService {

    ZLMediaKit getZLMediaKit();

    JSONObject getZLMServerConfig(ZLMediaKitQuery query);

    SanaResult updateZLMServerConfig(ServerConfigUpdate jsonOject);
}

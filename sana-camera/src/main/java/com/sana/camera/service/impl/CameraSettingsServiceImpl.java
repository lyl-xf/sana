package com.sana.camera.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.base.utils.HttpClientUtils;
import com.sana.camera.entity.query.ZLMediaKitQuery;
import com.sana.camera.entity.update.ServerConfigUpdate;
import com.sana.camera.proties.Nodes;
import com.sana.camera.proties.ZLMediaKit;
import com.sana.camera.service.CameraSettingsService;
import io.lettuce.core.json.JsonObject;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author LON
 * @create 2025/11/4 17:03
 */
@Slf4j
@Service
public class CameraSettingsServiceImpl implements CameraSettingsService {

    @Resource
    private ZLMediaKit zLMediaKit;

    @Override
    public ZLMediaKit getZLMediaKit() {
        return zLMediaKit;
    }

    @Override
    public JSONObject getZLMServerConfig(ZLMediaKitQuery query) {

        String secret = null;
        for (Nodes node : zLMediaKit.getNodes()){
            if (node.getHost().equals(query.getHost()) && node.getServerId().equals(query.getServerId())){
                secret = node.getSecret();
            }
        }
        String urls = "http://" + query.getHost() +"/index/api/getServerConfig?secret="+secret;
        String path = HttpClientUtils.doGet(urls);
        JSONObject datas = JSONObject.parseObject(String.valueOf(path), JSONObject.class);

        return datas;
    }

    @Override
    public SanaResult updateZLMServerConfig(ServerConfigUpdate jsonOject) {
            // 转换为 JsonNode
            JSONObject jsonObject = JSON.parseObject(jsonOject.getServerConfig());
            // 访问字段
            String apiSecret = jsonObject.get("api.secret").toString();

        return null;
    }
}

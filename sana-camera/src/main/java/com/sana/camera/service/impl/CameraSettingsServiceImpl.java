package com.sana.camera.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sana.base.syshandle.exception.SanaException;
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

import java.util.HashMap;
import java.util.Map;

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
    public SanaResult<JSONObject> getZLMServerConfig(ZLMediaKitQuery query) {
        try {
            String secret = getZLMServerConfigSecret(query.getHost(),query.getServerId());
            String urls = "http://" + query.getHost() +"/index/api/getServerConfig?secret="+secret;
            String path = HttpClientUtils.doGet(urls);
            JSONObject datas = JSONObject.parseObject(String.valueOf(path), JSONObject.class);
            if(datas!=null){
                return SanaResult.ok(datas);
            }else {
                return SanaResult.error("请检查ZLMServerConfi相关配置");
            }
        }catch (Exception e){
            log.error("获取ZLMServerConfig失败：{}", e.getMessage());
            throw new SanaException("获取ZLMServerConfig失败："+e.getMessage());
        }
    }

    @Override
    public SanaResult updateZLMServerConfig(ServerConfigUpdate jsonOject) {
            // 转换为 JsonNode
        JSONObject jsonObject = JSON.parseObject(jsonOject.getServerConfig());
        String secret = getZLMServerConfigSecret(jsonObject.getString("zlmNodeHosts"),jsonObject.getString("zlmNodeServerId"));
        // 访问字段
        String urls = "http://" + jsonObject.getString("zlmNodeHosts") +"/index/api/setServerConfig";
        jsonObject.remove("zlmNodeHosts");
        jsonObject.remove("zlmNodeServerId");
        jsonObject.put("secret",secret);
        String result = HttpClientUtils.doPostJson(urls, JSONObject.toJSONString(jsonObject));
        JSONObject jsonObjectResult = JSONObject.parseObject(result);
        if ("0".equals(jsonObjectResult.getString("code"))) {
           return SanaResult.ok();
        }else {
           return SanaResult.error();
        }
    }

    public String getZLMServerConfigSecret(String host,String serverId) {
        String secret = null;
        for (Nodes node : zLMediaKit.getNodes()) {
            if (node.getHost().equals(host) && node.getServerId().equals(serverId)) {
                secret = node.getSecret();
            }
        }
        return secret;
    }
}
package com.sana.camera.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.exception.SanaException;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.base.utils.HttpClientUtils;
import com.sana.camera.dao.CameraStreamProxyDao;
import com.sana.camera.entity.query.CameraStreamProxyQuery;
import com.sana.camera.entity.query.ZLMediaKitQuery;
import com.sana.camera.entity.result.CameraStreamProxyResult;
import com.sana.camera.entity.update.ServerConfigUpdate;
import com.sana.camera.proties.Nodes;
import com.sana.camera.proties.ZLMediaKit;
import com.sana.camera.service.CameraSettingsService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.sana.camera.entity.CameraStreamProxyEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LON
 * @create 2025/11/4 17:03
 */
@Slf4j
@Service
public class CameraSettingsServiceImpl extends BaseServiceImpl<CameraStreamProxyDao, CameraStreamProxyEntity> implements CameraSettingsService{

    @Resource
    private ZLMediaKit zLMediaKit;

    @Override
    public ZLMediaKit getZLMediaKit() {
        return zLMediaKit;
    }


    @Override
    public SanaPage<CameraStreamProxyResult> getStreamProxyPage(CameraStreamProxyQuery query) {
        IPage<CameraStreamProxyResult> page = baseMapper.getStreamProxyPage(getPage(query), query,true);
        return new SanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }


    @Override
    public SanaResult<JSONObject> getZLMServerConfig(ZLMediaKitQuery query) {
        try {
            String secret = getZLMServerConfigSecret(query.getHost(),query.getServerId());
            String urls = "http://" + query.getHost() +"/index/api/getServerConfig?secret="+secret;
            String path = HttpClientUtils.doGet(urls);
            JSONObject datas = JSONObject.parseObject(String.valueOf(path), JSONObject.class);
            if(datas!=null){
                if(datas.get("code").toString().equals("-100")){
                    return SanaResult.error("secret秘钥不对，请检查");
                }
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


    @Override
    public SanaResult restartServer(ZLMediaKitQuery jsonOject) {
        try {
            String secret = getZLMServerConfigSecret(jsonOject.getHost(),jsonOject.getServerId());
            String urls = "http://" + jsonOject.getHost() +"/index/api/restartServer?secret="+secret;
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
    public SanaResult addStreamProxy(CameraStreamProxyEntity cameraStreamProxy) {

        HashMap<String, String> StreamProxy = setStreamProxyUrl(cameraStreamProxy);
        try {
            String path = HttpClientUtils.doGet(StreamProxy.get("urls"));
            JSONObject datas = JSONObject.parseObject(String.valueOf(path), JSONObject.class);
            if(datas!=null){
                cameraStreamProxy.setNodeIp(StreamProxy.get("ip"));
                baseMapper.insert(cameraStreamProxy);
                return SanaResult.ok(datas);
                // 将数据加入到数据库中
            }else {
                return SanaResult.error("请检查ZLMServerConfi相关配置");
            }
        }catch (Exception e){
            log.error("获取ZLMServerConfig失败：{}", e.getMessage());
            throw new SanaException("获取ZLMServerConfig失败："+e.getMessage());
        }
    }


    @Override
    public SanaResult updateStreamProxy(CameraStreamProxyEntity cameraStreamProxy) {

        CameraStreamProxyEntity cameraStreamProxyEntity = baseMapper.selectById(cameraStreamProxy.getId());

        HashMap<String, String> StreamProxy = setStreamProxyUrl(cameraStreamProxy);
        Nodes nodeResult = getZLMServerConfigSecret(cameraStreamProxy.getNodeNameId());
        try {
            //删除
            String deleteStreamProxyUrl = "http://" + cameraStreamProxy.getNodeIp() +"/index/api/delStreamProxy?secret="+nodeResult.getSecret()+"&key="+cameraStreamProxyEntity.getVhost()+"/"+cameraStreamProxyEntity.getApp()+"/"+cameraStreamProxyEntity.getStream();
            String deleteResult = HttpClientUtils.doGet(deleteStreamProxyUrl);
            //重新添加
            String addStreamProxyUrl = HttpClientUtils.doGet(StreamProxy.get("urls"));
            JSONObject datas = JSONObject.parseObject(String.valueOf(addStreamProxyUrl), JSONObject.class);
            if(datas!=null){
                cameraStreamProxy.setNodeIp(StreamProxy.get("ip"));
                baseMapper.updateById(cameraStreamProxy);
                return SanaResult.ok(datas);
                // 将数据加入到数据库中
            }else {
                return SanaResult.error("请检查ZLMServerConfi相关配置");
            }

        }catch (Exception e){
            log.error("获取ZLMServerConfig失败：{}", e.getMessage());
            throw new SanaException("获取ZLMServerConfig失败："+e.getMessage());
        }
    }

    @Override
    public SanaResult<ZLMediaKit> delStreamProxy(String id) {
        try {
        // 删除
        CameraStreamProxyEntity cameraStreamProxyEntity = baseMapper.selectById(id);
        Nodes nodeResult = getZLMServerConfigSecret(cameraStreamProxyEntity.getNodeNameId());
        //删除
        String deleteStreamProxyUrl = "http://" + cameraStreamProxyEntity.getNodeIp() +"/index/api/delStreamProxy?secret="+nodeResult.getSecret()+"&key="+cameraStreamProxyEntity.getVhost()+"/"+cameraStreamProxyEntity.getApp()+"/"+cameraStreamProxyEntity.getStream();
        String deleteResult = HttpClientUtils.doGet(deleteStreamProxyUrl);

        baseMapper.deleteById( id);

        return SanaResult.ok();
        }catch (Exception e){
            log.error("获取ZLMServerConfig失败：{}", e.getMessage());
            throw new SanaException("获取ZLMServerConfig失败："+e.getMessage());
        }
    }


    /**
     * 拼接请求路径
     */
    public HashMap<String, String> setStreamProxyUrl(CameraStreamProxyEntity cameraStreamProxy) {

        HashMap<String, String> objectObjectHashMap = new HashMap<>();

        Nodes nodeResult = getZLMServerConfigSecret(cameraStreamProxy.getNodeNameId());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("http://");
        stringBuffer.append(nodeResult.getHost());
        stringBuffer.append("/index/api/addStreamProxy?secret=");
        stringBuffer.append(nodeResult.getSecret());
        stringBuffer.append("&vhost=");
        stringBuffer.append(cameraStreamProxy.getVhost());
        stringBuffer.append("&app=");
        stringBuffer.append(cameraStreamProxy.getApp());
        stringBuffer.append("&stream=");
        stringBuffer.append(cameraStreamProxy.getStream());
        stringBuffer.append("&url=");
        stringBuffer.append(cameraStreamProxy.getUrl());
        stringBuffer.append("&retry_count=");
        stringBuffer.append(cameraStreamProxy.getRetryCount());
        stringBuffer.append("&rtp_type=");
        stringBuffer.append(cameraStreamProxy.getRtpType());
        stringBuffer.append("&timeout_sec=");
        stringBuffer.append(cameraStreamProxy.getTimeoutSec());
        stringBuffer.append("&enable_hls=");
        stringBuffer.append(cameraStreamProxy.getEnableHls());
        stringBuffer.append("&enable_hls_fmp4=");
        stringBuffer.append(cameraStreamProxy.getEnableHlsFmp4());
        stringBuffer.append("&enable_mp4=");
        stringBuffer.append(cameraStreamProxy.getEnableMp4());
        stringBuffer.append("&enable_rtsp=");
        stringBuffer.append(cameraStreamProxy.getEnableRtsp());
        stringBuffer.append("&enable_rtmp=");
        stringBuffer.append(cameraStreamProxy.getEnableRtmp());
        stringBuffer.append("&enable_ts=");
        stringBuffer.append(cameraStreamProxy.getEnableTs());
        stringBuffer.append("&enable_fmp4=");
        stringBuffer.append(cameraStreamProxy.getEnableFmp4());
        stringBuffer.append("&hls_demand=");
        stringBuffer.append(cameraStreamProxy.getHlsDemand());
        stringBuffer.append("&rtsp_demand=");
        stringBuffer.append(cameraStreamProxy.getRtspDemand());
        stringBuffer.append("&rtmp_demand=");
        stringBuffer.append(cameraStreamProxy.getRtmpDemand());
        stringBuffer.append("&ts_demand=");
        stringBuffer.append(cameraStreamProxy.getTsDemand());
        stringBuffer.append("&fmp4_demand=");
        stringBuffer.append(cameraStreamProxy.getFmp4Demand());
        stringBuffer.append("&enable_audio=");
        stringBuffer.append(cameraStreamProxy.getEnableAudio());
        stringBuffer.append("&add_mute_audio=");
        stringBuffer.append(cameraStreamProxy.getAddMuteAudio());
        stringBuffer.append("&mp4_save_path=");
        stringBuffer.append(cameraStreamProxy.getMp4SavePath());
        stringBuffer.append("&mp4_max_second=");
        stringBuffer.append(cameraStreamProxy.getMp4MaxSecond());
        stringBuffer.append("&mp4_as_player=");
        stringBuffer.append(cameraStreamProxy.getMp4AsPlayer());
        stringBuffer.append("&hls_save_path=");
        stringBuffer.append(cameraStreamProxy.getHlsSavePath());
        if(cameraStreamProxy.getModifyStamp() !=3){
            stringBuffer.append("&modify_stamp=");
            stringBuffer.append(cameraStreamProxy.getModifyStamp());
        }
        stringBuffer.append("&auto_close=");
        stringBuffer.append(cameraStreamProxy.getAutoClose());

        objectObjectHashMap.put("urls",stringBuffer.toString());
        objectObjectHashMap.put("ip",nodeResult.getHost());
        return objectObjectHashMap;
    }


    /**
     * 获取Secret
     * @param serverId
     * @return
     */
    public Nodes getZLMServerConfigSecret(String serverId) {
        Nodes nodeResult = null;
        for (Nodes node : zLMediaKit.getNodes()) {
            if (node.getServerId().equals(serverId)) {
                nodeResult = node;
            }
        }
        return nodeResult;
    }


    /**
     * 获取Secret
     * @param host
     * @param serverId
     * @return
     */
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
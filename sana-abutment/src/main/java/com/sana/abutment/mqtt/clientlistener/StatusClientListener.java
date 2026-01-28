package com.sana.abutment.mqtt.clientlistener;

import com.alibaba.fastjson.JSONObject;
import com.sana.abutment.dao.DeviceStatusDao;
import com.sana.base.syshandle.entity.GeneralPrefix;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.dromara.mica.mqtt.spring.client.MqttClientSubscribe;
import org.dromara.mica.mqtt.spring.client.MqttClientTemplate;
import org.dromara.mica.mqtt.spring.client.event.MqttConnectedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author LON
 * @create 2025/10/23 15:41
 */
@Slf4j
@Service
public class StatusClientListener {

    @Resource
    private GeneralPrefix generalPrefix;
    @Resource
    private MqttClientTemplate client;

    @Resource
    private DeviceStatusDao deviceStatusDao;

    @EventListener
    public void onConnected(MqttConnectedEvent event) {
        log.info("MqttConnectedEvent:{}", event);
    }


    /**
     * 订阅代理状态
     * 规则配置，详见：doc/img/mqtt_proxy_rules.png
     * 转发格式：{"timestamp":1761211350277,"event":"client.disconnected","clientid":"lana-mqttClient"}
     * @return
     */
    @EventListener(ApplicationReadyEvent.class)
    public void thingSubRegister() {
        log.info("开始初始MQTT订阅设备状态主题:{}",generalPrefix.getProxyStatus());
        client.subQos0(generalPrefix.getProxyStatus(), (context, topic, message, payload) -> {
            //log.info("{}\t{}", topic, new String(payload, StandardCharsets.UTF_8));
            JSONObject jsonObject = JSONObject.parseObject(new String(payload, StandardCharsets.UTF_8), JSONObject.class);
            deviceStatusDao.updataStatus(Long.valueOf(jsonObject.get("clientid").toString().substring(3)),jsonObject.get("event").equals("client.connected")?1:0);

        });
    }
}

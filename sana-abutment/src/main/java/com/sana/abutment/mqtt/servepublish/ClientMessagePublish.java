package com.sana.abutment.mqtt.servepublish;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.dromara.mica.mqtt.spring.client.MqttClientTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author LON
 * 代理连接，使用客户端进行发送消息
 * @create 2025/10/23 15:34
 */
@Slf4j
@Component
public class ClientMessagePublish {

    @Resource
    private MqttClientTemplate client;


    public boolean publish(String topic, String body) {
        client.publish(topic, body.getBytes(StandardCharsets.UTF_8));
        return true;
    }

}

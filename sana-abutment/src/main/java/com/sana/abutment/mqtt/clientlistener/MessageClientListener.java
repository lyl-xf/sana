package com.sana.abutment.mqtt.clientlistener;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.dromara.mica.mqtt.spring.client.MqttClientTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author LON
 * @create 2025/10/23 15:13
 */
@Slf4j
@Component
public class MessageClientListener {
    private static final Logger logger = LoggerFactory.getLogger(MessageClientListener.class);

    @Resource
    private MqttClientTemplate client;

    @Value("${mqtt.client.proxy-prefix}")
    private String proxyPrefix;



    public boolean sub() {
        client.subQos0("/"+proxyPrefix+"/#", (context, topic, message, payload) -> {
            logger.info(topic + '\t' + new String(payload, StandardCharsets.UTF_8));
        });
        return true;
    }

}

package com.sana.abutment.initializer.proties.mqttclient;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author LON
 * @create 2025/10/24 13:21
 */

@Data
@Component
@ConfigurationProperties(prefix = "mqtt.client")
public class ProtocolsMqttClientProperties {

    private Boolean enabled;
    private String ip;
    private Integer port;
    private String name;
    private String clientId;
    private String userName;
    private String password;
    private String globalSubscribe;
    private Integer timeout;
    private Boolean reconnect;
    private String reInterval;
    private String version;
    private String readBufferSize;
    private String maxBytesInMessage;
    private Integer keepAliveSecs;
    private String heartbeatMode;
    private String heartbeatTimeoutStrategy;
    private String cleanSession;
    private String sessionExpiryIntervalSecs;
    private String bizThreadPoolSize;



}

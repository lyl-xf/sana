package com.sana.abutment.initializer.proties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author LON
 * @create 2025/9/21
 */
@Data
@Component
@ConfigurationProperties(prefix = "mqtt.server")
public class ProtocolsMqttProperties {
    private Boolean enabled;
    private String ip;
    private Integer port;
    private String name;
    private Integer heartbeatTimeout;
    private String readBufferSize;
    private String maxBytesInMessage;
    private AuthProPerties auth;
    private Boolean debug;
    private Boolean statEnable;
    private Integer webPort;
    private Boolean websocketEnable;
    private Boolean httpEnable;
    private HttpBasicAuthPerties httpBasicAuth;
    private SslPerties ssl;
    private Integer linkType;



}

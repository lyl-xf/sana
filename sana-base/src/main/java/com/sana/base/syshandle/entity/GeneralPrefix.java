package com.sana.base.syshandle.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author LON
 * @create 2025/10/24 13:21
 */

@Data
@Component
@ConfigurationProperties(prefix = "sana.general-prefix")
public class GeneralPrefix {


    private String tablePrefix;
    private String deviceTopicPrefix;
    private String proxyPrefix;
    private String proxyStatus;


}

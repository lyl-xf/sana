package com.sana.base.framework.token.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LON
 * @create 2025/7/12
 */
@Component
@ConfigurationProperties(prefix = "sa-token.ignore-config.path")
public class IgnoreConfig {
    private List<String> path = new ArrayList<>();

    public List<String> getPath() {
        return path;
    }
}

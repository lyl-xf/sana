package com.sana.base.token.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LON
 * @create 2025/7/12
 * url忽略配置
 */
@Component
@ConfigurationProperties(prefix = "sa-token.ignore-config.paths")
public class IgnoreConfig {
    private List<String> paths = new ArrayList<>();

    public List<String> getPaths() {
        return paths;
    }
}

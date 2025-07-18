package com.sana.base.token.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LON
 * @create 2025/7/12
 * url忽略配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "sa-token.ignore-config")
public class SaTokenIgnoreConfig {

    private List<String> paths;

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

}

package com.sana.base.cache.redis.stream;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LON
 * @create 2025/9/21
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.data.redis.stream")
public class RedisStreamConfigProperties {

    // 队列容量
    private int batchSize;
    // 队列超时时间
    private int pollTimeoutSeconds;

    private List<StreamConfig> streams;


    public static class StreamConfig {
        private String name;
        private String group;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

    }
}

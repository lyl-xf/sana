package com.sana.base.threadpool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author LON
 * @create 2025/9/21
 */
@Configuration
public class ThreadPoolProperties {

    // 不同线程


    @Value("${spring.data.redis.stream.core-pool-size}")
    private int redisStreamCoreSize;

    @Value("${spring.data.redis.stream.max-pool-size}")
    private int redisStreamMaxSize;

    @Value("${spring.data.redis.stream.queue-capacity}")
    private int redisStreamQueueCapacity;

    @Value("${mqtt.mqtt-message.core-size}")
    private int mqttMessageCoreSize;

    @Value("${mqtt.mqtt-message.queue-capacity}")
    private int mqttMessageQueueCapacity;

    @Value("${sana.rule-action.core-size}")
    private int ruleActionCoreSize;

    @Value("${sana.rule-action.queue-capacity}")
    private int ruleActionQueueCapacity;

    public int getRedisStreamCoreSize() {
        return redisStreamCoreSize;
    }

    public void setRedisStreamCoreSize(int redisStreamCoreSize) {
        this.redisStreamCoreSize = redisStreamCoreSize;
    }

    public int getRedisStreamMaxSize() {
        return redisStreamMaxSize;
    }

    public void setRedisStreamMaxSize(int redisStreamMaxSize) {
        this.redisStreamMaxSize = redisStreamMaxSize;
    }

    public int getRedisStreamQueueCapacity() {
        return redisStreamQueueCapacity;
    }

    public void setRedisStreamQueueCapacity(int redisStreamQueueCapacity) {
        this.redisStreamQueueCapacity = redisStreamQueueCapacity;
    }

    public int getMqttMessageCoreSize() {
        return mqttMessageCoreSize;
    }

    public void setMqttMessageCoreSize(int mqttMessageCoreSize) {
        this.mqttMessageCoreSize = mqttMessageCoreSize;
    }

    public int getMqttMessageQueueCapacity() {
        return mqttMessageQueueCapacity;
    }

    public void setMqttMessageQueueCapacity(int mqttMessageQueueCapacity) {
        this.mqttMessageQueueCapacity = mqttMessageQueueCapacity;
    }

    public int getRuleActionCoreSize() {
        return ruleActionCoreSize;
    }

    public void setRuleActionCoreSize(int ruleActionCoreSize) {
        this.ruleActionCoreSize = ruleActionCoreSize;
    }

    public int getRuleActionQueueCapacity() {
        return ruleActionQueueCapacity;
    }

    public void setRuleActionQueueCapacity(int ruleActionQueueCapacity) {
        this.ruleActionQueueCapacity = ruleActionQueueCapacity;
    }
}

package com.sana.abutment.mqtt.servelistener;

import com.alibaba.fastjson.JSONObject;
import com.sana.base.cache.redis.CacheKeyBuilder;
import com.sana.base.cache.redis.RedisUtils;
import com.sana.base.cache.redis.stream.RedisStreamConfigProperties;
import com.sana.base.syshandle.entity.GeneralPrefix;
import com.sana.base.syshandle.enums.GeneralPrefixEnum;
import com.sana.base.utils.JsonUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.dromara.mica.mqtt.codec.MqttPublishMessage;
import org.dromara.mica.mqtt.codec.MqttQoS;
import org.dromara.mica.mqtt.core.server.event.IMqttMessageListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tio.core.ChannelContext;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;

/**
 * @author LON
 * @create 2025/9/21
 */
@Slf4j
@Component
public class MessageListener implements IMqttMessageListener {

    //aviator-path
    @Value("${sana.rule-priority}")
    private String rulePriority;

    @Resource
    private GeneralPrefix generalPrefix;

    @Value("${sana.rule-action.queue-type}")
    private String queueType;

    @Resource
    private RedisUtils redisCacheOps;
    @Resource
    private RedisStreamConfigProperties redisStreamConfigProperties;

    @Resource(name = "mqttMessageTaskExecutor")
    private ExecutorService mqttMessageExecutor;

    @Override
    public void onMessage(ChannelContext context, String clientId, String topic, MqttQoS qoS, MqttPublishMessage message) {
        //String payload = new String(message.getPayload(), StandardCharsets.UTF_8);
        log.info("clientId:{} message:{} payload:{}", clientId, message, new String(message.getPayload(), StandardCharsets.UTF_8));
        //发送数据
        mqttMessageExecutor.submit(() -> {

            try {
                if ("REDIS".equalsIgnoreCase(queueType)) {
                    //拼接测试数据
                    HashMap<String, Object> streamMap = new HashMap<>(2);
                    streamMap.put("deviceId", topic.substring(generalPrefix.getDeviceTopicPrefix().length()));
                    streamMap.put("data", JsonUtils.parseObject(new String(message.getPayload(), StandardCharsets.UTF_8), JSONObject.class));
                    //查询这个设备是否是属于定时规则的，如果是就往定时流中发送，如果不是就往监听流中
                    Object timingData = redisCacheOps.get(CacheKeyBuilder.deviceIdRuleJon(topic.substring(1)));
                    // 生成随机数决定发送到哪个流
                    Random random = new Random();
                    int randomIndex = random.nextInt(2); // 生成0或1
                    if (timingData != null) {
                        //如果配置中，定时任务优先，则直接发送到定时流中
                        if (rulePriority == "timing") {
                            redisCacheOps.addMap(redisStreamConfigProperties.getStreams().get(2).getName(), streamMap);
                            //log.info("发送到流：{} 中,数据为：{}",redisStreamConfigProperties.getStreams().get(2).getName(),streamMap);
                            //如果监听优先，则发送到监听流中
                        } else {
                            // 根据随机数发送到对应的流
                            if (randomIndex == 0) {
                                redisCacheOps.addMap(redisStreamConfigProperties.getStreams().get(0).getName(), streamMap);
                                //log.info("发送到流：{} 中,数据为：{}",redisStreamConfigProperties.getStreams().get(0).getName(),streamMap);
                            } else {
                                redisCacheOps.addMap(redisStreamConfigProperties.getStreams().get(1).getName(), streamMap);
                                //log.info("发送到流：{} 中,数据为：{}",redisStreamConfigProperties.getStreams().get(1).getName(),streamMap);
                            }
                        }
                    } else {
                        //如果这个规则不属于 定时规则，则不管是不是定时优先，都往监听流中发送
                        // 根据随机数发送到对应的流
                        if (randomIndex == 0) {
                            redisCacheOps.addMap(redisStreamConfigProperties.getStreams().get(0).getName(), streamMap);
                            //log.info("发送到流：{} 中,数据为：{}",redisStreamConfigProperties.getStreams().get(0).getName(),streamMap);
                        } else {
                            redisCacheOps.addMap(redisStreamConfigProperties.getStreams().get(1).getName(), streamMap);
                            //log.info("发送到流：{} 中,数据为：{}",redisStreamConfigProperties.getStreams().get(1).getName(),streamMap);
                        }
                    }
                }
            } catch (Exception e) {
                log.error("MQTT消息处理异常", e);
                //e.printStackTrace();
            }
        });
    }
}

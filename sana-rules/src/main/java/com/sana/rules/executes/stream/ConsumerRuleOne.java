package com.sana.rules.executes.stream;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.aviator.Expression;

import com.sana.base.cache.caffeine.CaffeineCacheManager;
import com.sana.base.cache.redis.CacheKeyBuilder;
import com.sana.base.cache.redis.RedisUtils;
import com.sana.base.cache.redis.stream.RedisStreamConfigProperties;
import com.sana.base.syshandle.enums.GeneralPrefixEnum;
import com.sana.base.syshandle.enums.RuleValueEnum;
import com.sana.base.utils.JsonUtils;
import com.sana.rules.executes.action.ActionDispatcher;
import com.sana.rules.executes.action.RulesActionHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LON
 * @data 2025/9/27
 */
@Slf4j
@Component
public class ConsumerRuleOne implements StreamListener<String, MapRecord<String, String, String>> {

    /**
     * 注入Redis缓存操作工具类。
     */
    private final RedisUtils redisUtils;

    @Resource
    private RedisStreamConfigProperties redisStreamConfigProperties;

    @Resource
    private RulesActionHandler rulesActionHandler;

    @Resource
    private ActionDispatcher actionDispatcher;


    /**
     * 构造函数，注入redisUtils。
     *
     * @param redisUtils Redis缓存操作工具类
     */
    public ConsumerRuleOne(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    /**
     * 处理接收到的消息。
     *
     * @param message 接收到的消息
     */
    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        // 获取消息的ID
        RecordId id = message.getId();
        // 获取消息的内容
        Map<String, String> map = message.getValue();
        String deviceId = map.get("deviceId").replaceAll("^\"|\"$", "");
        String key = CacheKeyBuilder.deviceIdRule(GeneralPrefixEnum.TABLE_PREFIX.getValue()+deviceId);
        Integer ruleId = (Integer) redisUtils.get(key);
        JSONObject jsonObject = JsonUtils.parseObject(map.get("data"),  JSONObject.class);
        //如果没有对应的规则，则直接入库。
        if(ruleId!=null){
            Integer rulesType = null;
            //检查是什么类型的。
            String RulesListenKey = CacheKeyBuilder.rulesType(ruleId.toString());
            Object rulesTypeData = redisUtils.get(RulesListenKey);
            if (rulesTypeData != null) {
                rulesType = (Integer) rulesTypeData;
            }
            //后期考虑走本地缓存
            //Integer rulesType = CaffeineCacheManager.get("RulesType",ruleId);
            if (rulesType != null) {
                //type==4(数据上报====>条件过滤====>动作触发[设备控制、数据转发])
                if(rulesType == RuleValueEnum.NODECONDITIONAL.getValue()){
                    //拿到设备数据之后，开始匹配规则，查看是否有对应的规则匹配，如果有则进行规则处理，并将数据存入redis队列中，进行数据存储
                    jsonObject.put("ruleId",ruleId);
                    jsonObject.put("deviceId",Long.valueOf(deviceId));
                    Expression compiledExpression = CaffeineCacheManager.get("AviatorScript",ruleId + GeneralPrefixEnum.AVIATORSCRIPT_SUFFIX.getValue());
                    if(compiledExpression!=null) {
                        try {
                            Object result = compiledExpression.execute(jsonObject);
                            List<Map<String, Object>> resultList = (List<Map<String, Object>>) result;
                            for (Map<String, Object> actionMap : resultList) {
                                // 提交异步任务
                                actionDispatcher.dispatch(() -> {
                                    try {
                                        rulesActionHandler.handle(actionMap);
                                    } catch (Exception e) {
                                        System.err.println("规则动作执行失败: " + e.getMessage());
                                    }
                                });
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                // type==1(数据上报====>动作触发[设备控制、数据转发])
                if(rulesType == RuleValueEnum.NODEDEVICECONTROL.getValue()){
                    String RulesActionMap = CaffeineCacheManager.get("RulesActionMap",ruleId);
                    HashMap<String, Object> actionMap = new HashMap<>();
                    actionMap.put("message","[设备控制、数据转发]规则");
                    actionMap.put("actionMap",RulesActionMap);
                    actionDispatcher.dispatch(() -> {
                        try {
                            rulesActionHandler.handle(actionMap);
                        } catch (Exception e) {
                            System.err.println("规则动作执行失败: " + e.getMessage());
                        }
                    });
                }
                // type==2(数据上报====>动作触发[数据转发])
                if(rulesType == RuleValueEnum.NODEDEVICECONTROL.getValue()){
                    String RulesActionMap = CaffeineCacheManager.get("RulesActionMap",ruleId);
                    HashMap<String, Object> actionMap = new HashMap<>();
                    actionMap.put("message","[数据转发]规则");
                    actionMap.put("actionMap",RulesActionMap);
                    actionDispatcher.dispatch(() -> {
                        try {
                            rulesActionHandler.handle(actionMap);
                        } catch (Exception e) {
                            System.err.println("规则动作执行失败: " + e.getMessage());
                        }
                    });
                }
            }
        }
        //入库
        String queueKey = CacheKeyBuilder.mqttScript();
        jsonObject.put("deviceId",deviceId);
        redisUtils.leftPush(queueKey, jsonObject);

        // 确认消息
        redisUtils.ack(message.getStream(), redisStreamConfigProperties.getStreams().get(1).getGroup(), id.getValue());
        // 删除消息
        redisUtils.del(message.getStream(), id.getValue());
    }
}
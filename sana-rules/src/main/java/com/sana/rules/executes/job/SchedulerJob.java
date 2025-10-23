package com.sana.rules.executes.job;

import com.alibaba.fastjson.JSONObject;
import com.sana.abutment.mqtt.servepublish.MessagePublish;
import com.sana.base.cache.caffeine.CaffeineCacheManager;
import com.sana.base.cache.redis.CacheKeyBuilder;
import com.sana.base.cache.redis.RedisUtils;
import com.sana.base.syshandle.entity.DeviceControl;
import com.sana.base.syshandle.enums.GeneralPrefixEnum;
import com.sana.base.syshandle.enums.RuleValueEnum;
import com.sana.base.utils.JsonUtils;
import com.sana.rules.executes.action.ActionDispatcher;
import com.sana.rules.executes.action.RulesActionHandler;
import com.sana.rules.service.RulesItemQuratzService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @author LON
 * @data 2025/9/27
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public class SchedulerJob implements Job {

    @Resource
    private RulesItemQuratzService rulesItemQuratzService;

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private MessagePublish messagePublish;
    @Resource
    private ActionDispatcher actionDispatcher;
    @Resource
    private RulesActionHandler rulesActionHandler;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        /*log.info("\n\t----------------------------------------------------------------------------\n\t" +
                "=========================定时任务调度，执行业务逻辑====================:\n\t" +
                "jobName:{}\n\t" +
                "jobGroup:{}\n\t" +
                "triggerName:{}\n\t" +
                "triggerGroup:{}\n\t" +
                "上次触发时间:{}\n\t" +
                "本次触发时间:{}\n\t" +
                "下次触发时间:{}\n\t" +
                "调度时间:{}\n\t" +
                "本次出发内容:{}\n\t" +
                "----------------------------------------------------------------------------",
                context.getJobDetail().getKey().getName(),
                context.getJobDetail().getKey().getGroup(),
                context.getTrigger().getKey().getName(),
                context.getTrigger().getKey().getGroup(),
                DateUtil.formatDateTime(context.getPreviousFireTime()),
                DateUtil.formatDateTime(context.getFireTime()),
                DateUtil.formatDateTime(context.getNextFireTime()),
                DateUtil.formatDateTime(context.getScheduledFireTime()),
                "规则引擎，定时操作设备规则"
                );*/
        //todo 后续优化考虑将定时任务对应的规则id中，要推送的设备id缓存进入redis，然后再使用的时候根据任务信息进行查询
        // 目前进行使用数据库查询
        Long ruleId = rulesItemQuratzService.getRuleJobPushDevice(context.getJobDetail().getKey().getGroup(),context.getTrigger().getKey().getGroup());

        if(ruleId !=null){
            //检查规则类型，[设备控制、数据转发、条件规则]
            //int RulesType = CaffeineCacheManager.get("RulesType",ruleId);
            Integer rulesType = null;
            //检查是什么类型的。
            String RulesListenKey = CacheKeyBuilder.rulesType(ruleId.toString());
            Object rulesTypeData = redisUtils.get(RulesListenKey);
            if (rulesTypeData != null) {
                rulesType = (Integer) rulesTypeData;
            }
            if(rulesType == RuleValueEnum.NODEDEVICECONTROL.getValue()){
                String RulesActionMap = CaffeineCacheManager.get("RulesActionMap",ruleId);
                HashMap<String, Object> actionMap = new HashMap<>();
                actionMap.put("message","[设备控制、数据转发]规则");
                actionMap.put("actionMap",RulesActionMap);
                actionDispatcher.dispatch(() -> {
                    try {
                        //动作执行
                        rulesActionHandler.handle(actionMap);
                    } catch (Exception e) {
                        System.err.println("规则动作执行失败: " + e.getMessage());
                    }
                });
            }
            // type==2(数据上报====>动作触发[数据转发])
            if(rulesType == RuleValueEnum.NODEDATAPORTERING.getValue()){
                String RulesActionMap = CaffeineCacheManager.get("RulesActionMap",ruleId);
                HashMap<String, Object> actionMap = new HashMap<>();
                actionMap.put("message","[数据转发]规则");
                actionMap.put("actionMap",RulesActionMap);
                actionDispatcher.dispatch(() -> {
                    try {
                        //动作执行
                        rulesActionHandler.handle(actionMap);
                    } catch (Exception e) {
                        System.err.println("规则动作执行失败: " + e.getMessage());
                    }
                });
            }
            // type==4(数据上报====>动作触发[设备控制、数据转发])
            if(rulesType == RuleValueEnum.NODECONDITIONAL.getValue()){
                //根据规则id查询规则缓存中的设备id，拼接对应的采集指令，进行采集数据
                String rulesJobPushDeviceKey = CacheKeyBuilder.rulesJobPushDevice(GeneralPrefixEnum.RULE_PREFIX.getValue() + ruleId);
                Object rulesJobPushDeviceData = redisUtils.get(rulesJobPushDeviceKey);
                JSONObject pushData = new JSONObject();
                if (rulesJobPushDeviceData != null) {
                    //System.out.println(rulesJobPushDeviceKey+"已经存在，不进行更新");
                    String deviceNub = (String)rulesJobPushDeviceData;
                    //System.out.println("要发送采集指令的设备："+deviceNub);
                    if (deviceNub.contains(",")) {
                        String[] deviceArray = deviceNub.split(",");
                        for (String deviceId : deviceArray){
                            if (!pushData.isEmpty()) {
                                pushData.clear();
                            }
                            String deviceControlKey = CacheKeyBuilder.deviceControl(deviceId);
                            Object deviceControlData = redisUtils.get(deviceControlKey);
                            if(deviceControlData!=null){
                                List<DeviceControl> deviceControlList = (List<DeviceControl>)deviceControlData;
                                log.info("设备控制数据："+deviceControlList);
                                //一般这里就一个，但是也不排除有两个采集的指令，所以这里暂时用for来处理，没什么大的性能影响
                                for (DeviceControl deviceControlItem : deviceControlList){
                                    if(deviceControlItem.getControlType()==RuleValueEnum.ACQUISITIONFUNCTION.getValue()){
                                        pushData.put(deviceControlItem.getControlMode(),deviceControlItem.getControlDefultValue());
                                        messagePublish.publishTopic(GeneralPrefixEnum.DEVICE_TOPIC_PREFIX.getValue()+deviceControlItem.getDeviceItemId(),JsonUtils.toJsonString(pushData));
                                        // todo 这里需要记录日志，记录发送成功
                                        log.info("设备{},功能{}发送成功",deviceControlItem.getDeviceItemId(),deviceControlItem.getControlName());
                                    }
                                }
                            }
                        }
                    }else {
                        String deviceControlKey = CacheKeyBuilder.deviceControl(deviceNub);
                        Object deviceControlData = redisUtils.get(deviceControlKey);
                        if(deviceControlData!=null){
                            List<DeviceControl> deviceControlList = (List<DeviceControl>)deviceControlData;
                            log.info("设备控制数据："+deviceControlList);
                            for (DeviceControl deviceControlItem : deviceControlList){
                                if(deviceControlItem.getControlType()==RuleValueEnum.ACQUISITIONFUNCTION.getValue()){
                                    pushData.put(deviceControlItem.getControlMode(),deviceControlItem.getControlDefultValue());
                                    messagePublish.publishTopic(GeneralPrefixEnum.DEVICE_TOPIC_PREFIX.getValue()+deviceControlItem.getDeviceItemId(),JsonUtils.toJsonString(pushData));
                                    log.info("设备{},功能{}发送成功",deviceControlItem.getDeviceItemId(),deviceControlItem.getControlName());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

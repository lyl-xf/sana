package com.sana.rules.initializer;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.sana.abutment.initializer.proties.ProtocolsMqttProperties;
import com.sana.abutment.service.ProtocolsMqttService;
import com.sana.base.cache.caffeine.CaffeineCacheManager;
import com.sana.base.cache.redis.CacheKeyBuilder;
import com.sana.base.cache.redis.RedisUtils;
import com.sana.base.cache.redis.stream.RedisStreamConfigProperties;
import com.sana.base.syshandle.entity.DeviceControl;
import com.sana.base.syshandle.enums.GeneralPrefixEnum;
import com.sana.base.syshandle.exception.SanaException;
import com.sana.rules.dao.RulesItemDao;
import com.sana.rules.entity.*;
import com.sana.rules.service.DataAnalysisService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author LON
 * @data 2025/9/27
 */
@Component
@Slf4j
public class AbutmentInitializer {
    @Resource
    private ProtocolsMqttProperties protocolsMqttProperties;
    @Resource
    private ProtocolsMqttService protocolsMqttService;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private RedisStreamConfigProperties redisStreamConfigProperties;
    @Resource
    private RulesItemDao rulesItemDao;
    @Resource
    private DataAnalysisService dataAnalysisService;


    @Value("${lana.aviator-path}")
    private String aviatorPath;

    @PostConstruct
    public void initialize() {
        // todo 后期可根据实际需求进行多线程处理
        log.info("开始初始化...");
        executeStep(this::initMqttConfig, "MQTT配置");
        executeStep(this::initDeviceModelCache, "设备物模型缓存");
        executeStep(this::initDeviceControlCache, "设备服务缓存");
        executeStep(this::initRulesJobPushDeviceCache, "规则下发采集指令缓存");
        executeStep(this::initDeviceIdRuleJobCache, "定时任务设备ID规则缓存");
        executeStep(this::initActionMapDataCache, "动作处理缓存");
        executeStep(this::initUnconditionalCache, "非条件类型规则缓存");
        executeStep(this::initDeviceIdRuleListenCache, "监听规则设备ID缓存");
        executeStep(this::initRedisStreams, "Redis Stream");
        executeStep(this::initAviatorScripts, "Aviator脚本与规则类型");
        log.info("==============所有初始化步骤完成==============");

    }


    // 通用执行器：隔离异常、记录日志
    private void executeStep(Runnable step, String stepName) {
        try {
            log.info("开始初始化: {}", stepName);
            step.run();
            log.info("初始化成功: {}", stepName);
        } catch (Exception e) {
            log.error("初始化失败: {}", stepName, e);
        }
    }

    // 初始化 MQTT 配置
    private void initMqttConfig() {
        protocolsMqttService.initializeMqtt(protocolsMqttProperties);
        log.info("MQTT配置初始化完毕");
    }

    private void initDeviceModelCache() {
        protocolsMqttService.initializeCache();
        log.info("设备物模型缓存初始化完毕");
    }

        // todo 待处理初始化规则脚本文件与初始化td数据库表结构。
        //log.info("开始初始化根据设备生成TD数据库表结构......");
        //protocolsMqttService.initializeCache();
        //log.info("初始化完毕......");

        //log.info("开始初始化AS规则脚本文件......");
        //protocolsMqttService.initializeCache();
        //log.info("初始化完毕......");

    private void initRedisStreams() {
        boolean exists1 = redisUtils.isStreamExists(redisStreamConfigProperties.getStreams().get(0).getName());
        if (exists1) {
            log.info("已存在对应的stream：{}", redisStreamConfigProperties.getStreams().get(0).getName());
        } else {
            redisUtils.createGroup(redisStreamConfigProperties.getStreams().get(0).getName(), redisStreamConfigProperties.getStreams().get(0).getGroup());
            log.info("Redis Stream：{},不存在，开始创建并初始化完毕：", redisStreamConfigProperties.getStreams().get(0).getName());
        }

        boolean exists2 = redisUtils.isStreamExists(redisStreamConfigProperties.getStreams().get(1).getName());
        if (exists2) {
            log.info("已存在对应的stream：{}",redisStreamConfigProperties.getStreams().get(1).getName());
        } else {
            redisUtils.createGroup(redisStreamConfigProperties.getStreams().get(1).getName(),redisStreamConfigProperties.getStreams().get(1).getGroup());
            log.info("Redis Stream：{},不存在，开始创建并初始化完毕",redisStreamConfigProperties.getStreams().get(1).getName());
        }

        boolean exists3 = redisUtils.isStreamExists(redisStreamConfigProperties.getStreams().get(2).getName());
        if (exists3) {
            log.info("已存在对应的stream：{}",redisStreamConfigProperties.getStreams().get(2).getName());
        } else {
            redisUtils.createGroup(redisStreamConfigProperties.getStreams().get(2).getName(),redisStreamConfigProperties.getStreams().get(2).getGroup());
            log.info("Redis Stream：{},不存在，开始创建并初始化完毕",redisStreamConfigProperties.getStreams().get(2).getName());
        }
    }


    private void initActionMapDataCache() {

        try {
            List<RulesActionInstancEntity> actionInstancList = rulesItemDao.getActionMapData();
            for (RulesActionInstancEntity actionInstancEntity : actionInstancList){
                Object data = redisUtils.get(actionInstancEntity.getAcCode());
                if (data != null) {
                } else {
                    Map<String, Object> restoredMap = JSON.parseObject(actionInstancEntity.getAcInstancing());

                    redisUtils.set(actionInstancEntity.getAcCode(),restoredMap);
                }
            }
            log.info("动作初始化完毕");
        }catch (Exception e){
            log.error("动作初始化完毕: {}",e);
        }

    }

    /**
     * 初始化设备服务，这个设备有多少功能，用于定时采集（deviceControl）
     */
    private void initDeviceControlCache() {
        List<Long> deviceItemIds = rulesItemDao.getDeviceItemIds();
        for (Long deviceItemId : deviceItemIds) {
            String RulesJobKey = CacheKeyBuilder.deviceControl(deviceItemId.toString());
            Object RulesJobData = redisUtils.get(RulesJobKey);
            if (RulesJobData != null) {
                //System.out.println(RulesJobKey+"已经存在，不进行更新");
            } else {
                ArrayList<DeviceControl> deviceControlList = rulesItemDao.getDeviceControlList(deviceItemId);
                redisUtils.set(RulesJobKey, deviceControlList);
            }
        }
    }



    /**
     * 用于缓存非定时性规则的动作信息
     */
    private void initUnconditionalCache() {
        List<RulesActionInstancEntity> actionInstancList = rulesItemDao.getUnconditionalCache();
        for (RulesActionInstancEntity rulesActionInstancEntity : actionInstancList) {
            if(rulesActionInstancEntity != null){
                CaffeineCacheManager.set("RulesActionMap",  rulesActionInstancEntity.getRuleId(), rulesActionInstancEntity.getAcCode(), 0, TimeUnit.MINUTES);
            }
        }
    }


    /**
     * 初始化定时任务中，根据规则查询出来所有需要下发采集指令的数据 至缓存中（rules_job_push_device）
     */
    private void initRulesJobPushDeviceCache() {
        List<RulesJobPushDeviceEntity> rulesJobPushDeviceEntityList = rulesItemDao.getRulesJobPushDevice();
        for (RulesJobPushDeviceEntity rulesJobPushDeviceEntity : rulesJobPushDeviceEntityList) {
            String rulesJobPushDeviceKey = CacheKeyBuilder.rulesJobPushDevice(GeneralPrefixEnum.RULE_PREFIX.getValue() + rulesJobPushDeviceEntity.getRuleId());
            Object rulesJobPushDeviceData = redisUtils.get(rulesJobPushDeviceKey);
            if (rulesJobPushDeviceData != null) {
                //System.out.println(rulesJobPushDeviceKey+"已经存在，不进行更新");
            } else {
                redisUtils.set(rulesJobPushDeviceKey, rulesJobPushDeviceEntity.getDeviceIds());
            }
        }
    }

    /**
     * 初始化定时任务中，检索设备信息是否属于定时任务的缓存中（rules_job_device_id,对应的redis为： lana:deviceIdRuleJob）
     */
    private void initDeviceIdRuleJobCache() {
        List<RulesJobDeviceIdEntity> rulesJobDeviceIdEntityList = rulesItemDao.getRulesJobDeviceId();
        for (RulesJobDeviceIdEntity rulesJobDeviceIdEntity : rulesJobDeviceIdEntityList) {
            String RulesJobKey = CacheKeyBuilder.deviceIdRuleJon(GeneralPrefixEnum.TABLE_PREFIX.getValue() + rulesJobDeviceIdEntity.getDeviceId());
            Object RulesJobData = redisUtils.get(RulesJobKey);
            if (RulesJobData != null) {
                //System.out.println(RulesJobKey+"已经存在，不进行更新");
            } else {
                redisUtils.set(RulesJobKey, rulesJobDeviceIdEntity.getRuleId());
            }
        }
    }

    /**
     * 初始化监听规则中，每个设备属于哪个规则（rules_listen_device_id，对应的redis为： lana:deviceIdRule:）
     */
    private void initDeviceIdRuleListenCache() {
        List<RulesListenDeviceIdEntity> rulesListenDeviceIdEntity = rulesItemDao.getRulesListenDeviceIdList();
        for (RulesListenDeviceIdEntity rulesListenDeviceIdEntity1 : rulesListenDeviceIdEntity) {
            String RulesListenKey = CacheKeyBuilder.deviceIdRule(GeneralPrefixEnum.TABLE_PREFIX.getValue() + rulesListenDeviceIdEntity1.getDeviceId());
            Object RulesListenData = redisUtils.get(RulesListenKey);
            if (RulesListenData != null) {
                //System.out.println("deviceIdRule已经存在，不进行更新");
            } else {
                redisUtils.set(RulesListenKey, rulesListenDeviceIdEntity1.getRuleId());
            }
        }
    }


    /**
     * 初始化规则脚本。
      */
    private void initAviatorScripts() {
        List<RulesItemEntity> rulesItemEntity = rulesItemDao.selectList(new QueryWrapper<RulesItemEntity>().eq("deleted",0));
        try {
            for (RulesItemEntity rulesItemEntity1 : rulesItemEntity) {

                String RulesListenKey = CacheKeyBuilder.rulesType(rulesItemEntity1.getId().toString());
                Object RulesListenData = redisUtils.get(RulesListenKey);
                if (RulesListenData != null) {
                    //System.out.println("deviceIdRule已经存在，不进行更新");
                } else {
                    redisUtils.set(RulesListenKey, rulesItemEntity1.getRuleType());
                }
                //CaffeineCacheManager.set("RulesType",  rulesItemEntity1.getId(), rulesItemEntity1.getRuleType(), 0, TimeUnit.MINUTES);
                log.info("CaffeineCache:{},缓存成功", rulesItemEntity1.getId());
                if (rulesItemEntity1.getAsPath() != null && !rulesItemEntity1.getAsPath().isEmpty()) {
                    Path pathFile = Paths.get(rulesItemEntity1.getAsPath());
                    if (Files.exists(pathFile)) {
                        //处理缓存中
                        Expression exp = AviatorEvaluator.getInstance().compileScript(String.valueOf(rulesItemEntity1.getAsPath()), true);//编译脚本文件
                        CaffeineCacheManager.set("AviatorScript", rulesItemEntity1.getId() + GeneralPrefixEnum.AVIATORSCRIPT_SUFFIX.getValue(), exp, 0, TimeUnit.MINUTES);
                    } else if (!Files.exists(pathFile)) {
                        // 创建新文件
                        Files.createFile(pathFile);
                        try (BufferedWriter writer = Files.newBufferedWriter(pathFile)) {
                            writer.write(rulesItemEntity1.getAsContent());
                            log.info("新文件 {} 已经创建并写入成功",rulesItemEntity1.getAsPath());
                        }
                        Expression exp = AviatorEvaluator.getInstance().compileScript(String.valueOf(rulesItemEntity1.getAsPath()), true);//编译脚本文件
                        CaffeineCacheManager.set("AviatorScript", rulesItemEntity1.getId() + GeneralPrefixEnum.AVIATORSCRIPT_SUFFIX.getValue(), exp, 0, TimeUnit.MINUTES);
                    } else if (Files.isDirectory(pathFile.getParent())) {
                        addFile(rulesItemEntity1);
                    }
                }else {
                    addFile(rulesItemEntity1);
                }

            }
        } catch (Exception e) {
            throw new SanaException("文件创建或写入失败");
        }
    }
    /**
     * 添加文件
     */
    public void addFile(RulesItemEntity rulesItemEntity1) throws Exception{
        String targetDirectory = null;
        if (aviatorPath == null || aviatorPath.isEmpty()) {
            String userDir = System.getProperty("user.dir");
            String scriptPath = Paths.get(userDir, "lana-rules", "src", "main", "resources", "script").toString();
            targetDirectory = scriptPath;
        } else {
            targetDirectory = aviatorPath;
        }
        String fileName = rulesItemEntity1.getId() + GeneralPrefixEnum.AVIATORSCRIPT_SUFFIX.getValue();
        Path filePath = Paths.get(targetDirectory, fileName);
        // 创建目录（如果不存在）
        Files.createDirectories(filePath.getParent());
        // 创建文件并写入内容
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toString()))) {
            writer.write(rulesItemEntity1.getAsContent());
            System.out.println("文件创建并写入成功");
        }
        rulesItemEntity1.setAsPath(filePath.toString());
        rulesItemDao.updateRulesItemById(rulesItemEntity1.getId(),  rulesItemEntity1.getAsPath());


        log.info("Aviatorscript:RULES{} 初始化成功", rulesItemEntity1.getId() + GeneralPrefixEnum.AVIATORSCRIPT_SUFFIX.getValue());
    }
}

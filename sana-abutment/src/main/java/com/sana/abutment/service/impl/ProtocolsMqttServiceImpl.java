package com.sana.abutment.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sana.abutment.dao.ProtocolsMqttDao;
import com.sana.abutment.entity.InintProtocolsMqttEntity;
import com.sana.abutment.entity.ProtocolsMqttEntity;
import com.sana.abutment.entity.query.ProtocolsMqttQuery;
import com.sana.abutment.entity.result.ProtocolsMqttResult;
import com.sana.abutment.entity.result.RuleProtocolsMqttResult;
import com.sana.abutment.entity.save.ProtocolsMqttSave;
import com.sana.abutment.entity.update.ProtocolsMqttUpdate;
import com.sana.abutment.initializer.cacheproties.DeviceItemModeCacheSave;
import com.sana.abutment.initializer.proties.mqttclient.ProtocolsMqttClientProperties;
import com.sana.abutment.initializer.proties.mqttserve.ProtocolsMqttProperties;
import com.sana.abutment.service.ProtocolsMqttService;
import com.sana.base.cache.redis.CacheKeyBuilder;
import com.sana.base.cache.redis.RedisUtils;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.entity.GeneralPrefix;
import com.sana.base.syshandle.enums.GeneralPrefixEnum;
import com.sana.base.syshandle.exception.SanaException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LON
 * @create 2025/9/21 13:57
 */
@Slf4j
@Service
public class ProtocolsMqttServiceImpl extends BaseServiceImpl<ProtocolsMqttDao, ProtocolsMqttEntity> implements ProtocolsMqttService {


    @Resource
    private RedisUtils redisCacheOps;
    @Resource
    private ProtocolsMqttClientProperties protocolsMqttClientProperties;

    @Resource
    private GeneralPrefix generalPrefix;
    @Override
    public List<ProtocolsMqttResult> getMqttGroupPage(ProtocolsMqttQuery query) {
        List<ProtocolsMqttResult> page = baseMapper.getMqttGroupPage(query);
        return page;
    }
    @Override
    public List<RuleProtocolsMqttResult> getMqttGroupList(ProtocolsMqttQuery query) {
        List<RuleProtocolsMqttResult> page = baseMapper.getMqttGroupList(query);
        return page;
    }

    @Override
    public ProtocolsMqttResult getMqttBroker(String deviceId) {


        return baseMapper.getMqttBroker(Long.parseLong(deviceId));
    }



    @Override
    public void saveProtocolsMqttSave(ProtocolsMqttSave saveVO) {

        if(saveVO.getMqttType()==1){
            long orgCount = count(new QueryWrapper<ProtocolsMqttEntity>().eq("mqtt_type", 1).eq("mqtt_enabled",1));
            if(orgCount>0){
                throw new SanaException("本系统只有一个mqtt-broker，无法进行创建多个");
            }else {
                ProtocolsMqttEntity entity = new ProtocolsMqttEntity();
                BeanUtil.copyProperties(saveVO, entity);
                baseMapper.insert(entity);
            }
        }else {
            ProtocolsMqttEntity entity = new ProtocolsMqttEntity();
            BeanUtil.copyProperties(saveVO, entity);
            baseMapper.insert(entity);
        }
    }

    @Override
    public void updateProtocolsMqtt(ProtocolsMqttUpdate updateVo) {
        if(updateVo.getMqttType()==1){
            long orgCount = count(new QueryWrapper<ProtocolsMqttEntity>().eq("mqtt_type", 1).eq("mqtt_enabled",1));
            if(orgCount>0){
                throw new SanaException("本系统只有一个mqtt-broker，无法进行创建多个");
            }else {
                ProtocolsMqttEntity entity = new ProtocolsMqttEntity();
                BeanUtil.copyProperties(updateVo, entity);
                baseMapper.updateById(entity);
            }
        }else {
            ProtocolsMqttEntity entity = new ProtocolsMqttEntity();
            BeanUtil.copyProperties(updateVo, entity);
            baseMapper.updateById(entity);
        }

    }

    @Override
    public void deleteProtocolsMqtt(Long id) {
        //如果启用状态下，则不允许删除
        ProtocolsMqttEntity protocolsMqttEntity = baseMapper.selectById(id);
        if(protocolsMqttEntity.getMqttEnabled()==1){
            log.info("启用中，不允许删除");
            throw new SanaException("启用中，不允许删除");
        }else {
            baseMapper.deleteById(id);
        }
    }

    @Override
    public void initializeMqtt(ProtocolsMqttProperties protocolsMqttProperties) {
        InintProtocolsMqttEntity protocolsMqttUpdatate = new InintProtocolsMqttEntity();
        ProtocolsMqttEntity protocolsMqttEntity = baseMapper.selectOne(new QueryWrapper<ProtocolsMqttEntity>().eq("mqtt_type", 1));
        if(protocolsMqttEntity!=null){
            protocolsMqttUpdatate.setId(protocolsMqttEntity.getId());
            protocolsMqttUpdatate.setIp(protocolsMqttProperties.getIp());
            protocolsMqttUpdatate.setMqttName(protocolsMqttProperties.getName());
            protocolsMqttUpdatate.setMqttType(1);
            protocolsMqttUpdatate.setTcpPort(protocolsMqttProperties.getPort().toString());
            protocolsMqttUpdatate.setWebsocketPort(protocolsMqttProperties.getWebPort().toString());
            protocolsMqttUpdatate.setAuthEnable(protocolsMqttProperties.getAuth().getEnable() ? 1 : 0);
            protocolsMqttUpdatate.setUsername(protocolsMqttProperties.getAuth().getUsername());
            protocolsMqttUpdatate.setPassword(protocolsMqttProperties.getAuth().getPassword());
            protocolsMqttUpdatate.setHttpEnable(protocolsMqttProperties.getHttpEnable() ? 1 : 0);
            protocolsMqttUpdatate.setHttpBasicAuth(protocolsMqttProperties.getHttpBasicAuth().getEnable() ? 1 : 0);
            protocolsMqttUpdatate.setHttpBasicUsername(protocolsMqttProperties.getHttpBasicAuth().getUsername());
            protocolsMqttUpdatate.setHttpBasicPassword(protocolsMqttProperties.getHttpBasicAuth().getPassword());
            protocolsMqttUpdatate.setSslEnabled(protocolsMqttProperties.getSsl().getEnabled() ? 1 : 0);
            protocolsMqttUpdatate.setKeystorePath(protocolsMqttProperties.getSsl().getKeystorePath());
            protocolsMqttUpdatate.setKeystorePass(protocolsMqttProperties.getSsl().getKeystorePass());
            protocolsMqttUpdatate.setClientAuth(protocolsMqttProperties.getSsl().getClientAuth());
            protocolsMqttUpdatate.setTruststorePath(protocolsMqttProperties.getSsl().getTruststorePath());
            protocolsMqttUpdatate.setTruststorePass(protocolsMqttProperties.getSsl().getTruststorePass());
            protocolsMqttUpdatate.setMqttEnabled(protocolsMqttProperties.getEnabled() ? 1 : 0);
            protocolsMqttUpdatate.setLinkType(protocolsMqttProperties.getLinkType());
            protocolsMqttUpdatate.setCreateTime(new DateTime());
            protocolsMqttUpdatate.setUpdateTime(new DateTime());
            protocolsMqttUpdatate.setCreatorName("系统初始化");
            protocolsMqttUpdatate.setUpdaterName("系统初始化");
            protocolsMqttUpdatate.setLinkType(1);
            baseMapper.updateByProtocol(protocolsMqttUpdatate);
        }else {
            baseMapper.insertProtocol(protocolsMqttUpdatate);
        }
    }


    @Override
    public void initializeMqttClient(ProtocolsMqttClientProperties protocolsMqttClientProperties) {
        InintProtocolsMqttEntity protocolsMqttUpdatate = new InintProtocolsMqttEntity();
        ProtocolsMqttEntity protocolsMqttEntity = baseMapper.selectOne(new QueryWrapper<ProtocolsMqttEntity>().eq("mqtt_type", 2));
        if(protocolsMqttEntity!=null){
            protocolsMqttUpdatate.setId(protocolsMqttEntity.getId());
            protocolsMqttUpdatate.setIp(protocolsMqttClientProperties.getIp());
            protocolsMqttUpdatate.setMqttName(protocolsMqttClientProperties.getName());
            protocolsMqttUpdatate.setMqttType(2);
            protocolsMqttUpdatate.setTcpPort(protocolsMqttClientProperties.getPort().toString());
            protocolsMqttUpdatate.setUsername(protocolsMqttClientProperties.getUserName());
            protocolsMqttUpdatate.setPassword(protocolsMqttClientProperties.getPassword());
            protocolsMqttUpdatate.setLinkType(2);
            protocolsMqttUpdatate.setMqttEnabled(protocolsMqttClientProperties.getEnabled() ? 1 : 0);
            baseMapper.updateByProtocol(protocolsMqttUpdatate);
        }else {
            protocolsMqttUpdatate.setIp(protocolsMqttClientProperties.getIp());
            protocolsMqttUpdatate.setMqttName(protocolsMqttClientProperties.getName());
            protocolsMqttUpdatate.setMqttType(2);
            protocolsMqttUpdatate.setTcpPort(protocolsMqttClientProperties.getPort().toString());
            protocolsMqttUpdatate.setUsername(protocolsMqttClientProperties.getUserName());
            protocolsMqttUpdatate.setPassword(protocolsMqttClientProperties.getPassword());
            protocolsMqttUpdatate.setCreateTime(new DateTime());
            protocolsMqttUpdatate.setUpdateTime(new DateTime());
            protocolsMqttUpdatate.setCreatorName("系统初始化");
            protocolsMqttUpdatate.setUpdaterName("系统初始化");
            protocolsMqttUpdatate.setLinkType(2);
            protocolsMqttUpdatate.setMqttEnabled(protocolsMqttClientProperties.getEnabled() ? 1 : 0);
            baseMapper.insertProtocol(protocolsMqttUpdatate);
        }

    }

    @Override
    public ProtocolsMqttClientProperties getProtocolsMqttClientProperties() {
        return protocolsMqttClientProperties;
    }

    @Override
    public Integer getMttpType(String deviceId) {
        return baseMapper.getMttpType(Long.parseLong(deviceId));
    }

    @Override
    public void initializeCache() {
        List<DeviceItemModeCacheSave> deviceModeListSave = baseMapper.getDevicveeModeList();
        for (DeviceItemModeCacheSave deviceItemModeCacheSave : deviceModeListSave){
            String key = CacheKeyBuilder.deviceMode(generalPrefix.getTablePrefix()+deviceItemModeCacheSave.getDeviceItemId());
            Object data = redisCacheOps.get(key);
            if(data!=null){
                //redisCacheOps.delete(key);
                //redisCacheOps.set(key,deviceItemModeCacheSave.getModeSigns());

            }else {
                redisCacheOps.set(key,deviceItemModeCacheSave.getModeSigns());
            }
        }

    }

}

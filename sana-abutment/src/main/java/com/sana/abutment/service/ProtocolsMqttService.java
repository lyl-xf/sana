package com.sana.abutment.service;

import com.sana.abutment.entity.ProtocolsMqttEntity;
import com.sana.abutment.entity.query.ProtocolsMqttQuery;
import com.sana.abutment.entity.result.ProtocolsMqttResult;
import com.sana.abutment.entity.result.RuleProtocolsMqttResult;
import com.sana.abutment.entity.save.ProtocolsMqttSave;
import com.sana.abutment.entity.update.ProtocolsMqttUpdate;
import com.sana.abutment.initializer.proties.mqttclient.ProtocolsMqttClientProperties;
import com.sana.abutment.initializer.proties.mqttserve.ProtocolsMqttProperties;
import com.sana.base.mybatis.service.BaseService;
import jakarta.annotation.Resource;

import java.util.List;

/**
 * @author LON
 * @create 2025/9/21 13:57
 */
public interface ProtocolsMqttService extends BaseService<ProtocolsMqttEntity> {

    List<ProtocolsMqttResult> getMqttGroupPage(ProtocolsMqttQuery query);

    void saveProtocolsMqttSave(ProtocolsMqttSave saveVO);

    void updateProtocolsMqtt(ProtocolsMqttUpdate updateVo);

    void deleteProtocolsMqtt(Long id);

    void initializeMqtt(ProtocolsMqttProperties protocolsMqttProperties);

    void initializeCache();

    List<RuleProtocolsMqttResult> getMqttGroupList(ProtocolsMqttQuery query);

    ProtocolsMqttResult getMqttBroker();

    void initializeMqttClient(ProtocolsMqttClientProperties protocolsMqttClientProperties);

    ProtocolsMqttClientProperties getProtocolsMqttClientProperties();
}

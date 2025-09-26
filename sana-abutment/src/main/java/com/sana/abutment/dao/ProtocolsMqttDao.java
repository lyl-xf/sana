package com.sana.abutment.dao;

import com.sana.abutment.entity.InintProtocolsMqttEntity;
import com.sana.abutment.entity.ProtocolsMqttEntity;
import com.sana.abutment.entity.query.ProtocolsMqttQuery;
import com.sana.abutment.entity.result.ProtocolsMqttResult;
import com.sana.abutment.entity.result.RuleProtocolsMqttResult;
import com.sana.abutment.initializer.cacheproties.DeviceItemModeCacheSave;
import com.sana.base.mybatis.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LON
 * @create 2025/9/21
 */
@Mapper
public interface ProtocolsMqttDao extends BaseDao<ProtocolsMqttEntity> {


    List<ProtocolsMqttResult> getMqttGroupPage(@Param("model") ProtocolsMqttQuery query);

    void updateByProtocol(@Param("protocolsMqttUpdatate") InintProtocolsMqttEntity protocolsMqttUpdatate);

    void insertProtocol(@Param("protocolsMqttUpdatate")InintProtocolsMqttEntity protocolsMqttUpdatate);

    Long getProtocolsId(Long id);

    List<DeviceItemModeCacheSave> getDevicveeModeList();

    List<RuleProtocolsMqttResult> getMqttGroupList(@Param("model") ProtocolsMqttQuery query);

    ProtocolsMqttResult getMqttBroker();

}

package com.sana.abutment.convert;

import com.sana.abutment.entity.ProtocolsMqttEntity;
import com.sana.abutment.entity.save.ProtocolsMqttSave;
import com.sana.abutment.entity.update.ProtocolsMqttUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author LON
 * @create 2025/9/21
 */
@Mapper
public interface ProtocolsMqttConvert {

    ProtocolsMqttConvert INSTANCE = Mappers.getMapper(ProtocolsMqttConvert.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)
    ProtocolsMqttEntity convert(ProtocolsMqttSave saveVO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)
    ProtocolsMqttEntity convert(ProtocolsMqttUpdate saveVO);

}
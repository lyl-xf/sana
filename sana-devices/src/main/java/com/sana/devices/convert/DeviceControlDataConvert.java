package com.sana.devices.convert;

import com.sana.devices.entity.DeviceControlDataEntity;
import com.sana.devices.entity.vo.save.DeviceControlDataSave;
import com.sana.devices.entity.vo.update.DeviceControlDataUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Mapper
public interface DeviceControlDataConvert {
    DeviceControlDataConvert INSTANCE = Mappers.getMapper(DeviceControlDataConvert.class);

/*    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)*/
    DeviceControlDataEntity convert(DeviceControlDataSave saveVO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)
    DeviceControlDataEntity convert(DeviceControlDataUpdate updateVO);
}

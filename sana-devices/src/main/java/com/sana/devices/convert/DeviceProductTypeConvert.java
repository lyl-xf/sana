package com.sana.devices.convert;

import com.sana.devices.entity.DeviceProductTypeEntity;
import com.sana.devices.entity.vo.save.DeviceProductTypeSave;
import com.sana.devices.entity.vo.update.DeviceProductTypeUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Mapper
public interface DeviceProductTypeConvert {
    DeviceProductTypeConvert INSTANCE = Mappers.getMapper(DeviceProductTypeConvert.class);

/*    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)*/
    DeviceProductTypeEntity convert(DeviceProductTypeSave saveVO);

/*    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)*/
    DeviceProductTypeEntity convert(DeviceProductTypeUpdate saveVO);
}

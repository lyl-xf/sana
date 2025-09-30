package com.sana.devices.convert;

import com.sana.devices.entity.DeviceGroupEntity;
import com.sana.devices.entity.vo.save.DeviceGroupSave;
import com.sana.devices.entity.vo.update.DeviceGroupUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Mapper
public interface DeviceGroupConvert {
    DeviceGroupConvert INSTANCE = Mappers.getMapper(DeviceGroupConvert.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)
    DeviceGroupEntity convert(DeviceGroupSave saveVO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)
    DeviceGroupEntity convert(DeviceGroupUpdate updateVo);

}

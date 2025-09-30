package com.sana.devices.convert;

import com.sana.devices.entity.DeviceEdgesItemEntity;
import com.sana.devices.entity.vo.result.DeviceEdgesItemResult;
import com.sana.devices.entity.vo.save.DeviceEdgesItemSave;
import com.sana.devices.entity.vo.update.DeviceEdgesItemUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Mapper
public interface DeviceEdgesItemConvert {
    DeviceEdgesItemConvert INSTANCE = Mappers.getMapper(DeviceEdgesItemConvert.class);
    DeviceEdgesItemResult convert(DeviceEdgesItemEntity deviceEdgesItemEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)
    DeviceEdgesItemEntity convert(DeviceEdgesItemSave saveVo);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)
    DeviceEdgesItemEntity convert(DeviceEdgesItemUpdate updateVo);
}



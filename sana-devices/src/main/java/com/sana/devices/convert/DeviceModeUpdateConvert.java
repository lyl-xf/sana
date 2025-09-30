package com.sana.devices.convert;

import com.sana.devices.entity.DeviceModeEntity;
import com.sana.devices.entity.vo.update.DeviceModeUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface DeviceModeUpdateConvert {
    DeviceModeUpdateConvert INSTANCE = Mappers.getMapper(DeviceModeUpdateConvert.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)
    DeviceModeEntity convert(DeviceModeUpdate updataVO);
}

package com.sana.devices.convert;

import com.sana.devices.entity.DeviceItemEntity;
import com.sana.devices.entity.vo.save.DeviceItemSave;
import com.sana.devices.entity.vo.update.DeviceItemUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Mapper
public interface DeviceItemConvert {
    DeviceItemConvert INSTANCE = Mappers.getMapper(DeviceItemConvert.class);

    @Mapping(target = "productTypeShow", ignore = true)
    DeviceItemEntity convert(DeviceItemSave vo);

    @Mapping(target = "productTypeShow", ignore = true)
    DeviceItemEntity convert(DeviceItemUpdate vo);

}

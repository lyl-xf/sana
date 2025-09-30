package com.sana.devices.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.devices.entity.DeviceProductTypeEntity;
import com.sana.devices.entity.vo.query.DeviceProductTypeQuery;
import com.sana.devices.entity.vo.result.DeviceAbutmentResult;
import com.sana.devices.entity.vo.result.DeviceProductTypeResult;
import com.sana.devices.entity.vo.save.DeviceProductTypeSave;
import com.sana.devices.entity.vo.save.SaveDeviceAbutmentSave;
import com.sana.devices.entity.vo.update.DeviceProductTypeUpdate;

import java.util.List;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
public interface DeviceProductTypeService extends BaseService<DeviceProductTypeEntity> {

    List<DeviceProductTypeResult> getList(DeviceProductTypeQuery query);

    void saveDeviceProductType(DeviceProductTypeSave saveVO);

    void updateDeviceProductType(DeviceProductTypeUpdate updateVO);

    void deleteDeviceProductType(Long id);

    DeviceAbutmentResult getDeviceProtocolsMode(Long id);

    void saveDeviceProtocolsMode(SaveDeviceAbutmentSave saveVO);
}

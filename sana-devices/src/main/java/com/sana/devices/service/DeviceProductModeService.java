package com.sana.devices.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.devices.entity.DeviceProductModeEntity;
import com.sana.devices.entity.vo.result.DeviceProductModeResult;
import com.sana.devices.entity.vo.save.DeviceProductModeSave;

import java.util.List;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
public interface DeviceProductModeService extends BaseService<DeviceProductModeEntity> {

    List<DeviceProductModeResult> getProductModePage(Long id);

    void saveDeviceGroup(DeviceProductModeSave saveVO);
}

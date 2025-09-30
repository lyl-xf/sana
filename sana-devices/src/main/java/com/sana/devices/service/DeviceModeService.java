package com.sana.devices.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.devices.entity.DeviceModeEntity;
import com.sana.devices.entity.vo.result.ColumnResult;
import com.sana.devices.entity.vo.result.DeviceModeResult;
import com.sana.devices.entity.vo.result.DeviceProductModeResult;
import com.sana.devices.entity.vo.save.DeviceItemModeSave;
import com.sana.devices.entity.vo.update.DeviceModeUpdate;

import java.util.List;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
public interface DeviceModeService extends BaseService<DeviceModeEntity> {

    //继承自产品的物模型
    void saveDeviceMode(List<DeviceProductModeResult> productModeList, Long id);
    //新增物模型
    void saveDeviceModes(DeviceItemModeSave saveVO);
    List<DeviceModeResult> getDeviceItemModeList(Long id);

    List<DeviceModeResult> getDeviceContoleList(Long id);

    void saveContoleModel(DeviceModeUpdate updataVO);

    List<ColumnResult> getDeviceModeMap(Long deviceId);
}

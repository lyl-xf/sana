package com.sana.devices.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.devices.entity.DeviceControlDataEntity;
import com.sana.devices.entity.vo.query.DeviceControlDataQuery;
import com.sana.devices.entity.vo.result.DeviceControlDataResult;
import com.sana.devices.entity.vo.save.DeviceControlDataSave;
import com.sana.devices.entity.vo.update.DeviceControlDataUpdate;

import java.util.List;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
public interface DeviceControlDataService extends BaseService<DeviceControlDataEntity> {


    DeviceControlDataEntity saveDeviceControl(DeviceControlDataSave saveVO);

    void updateDeviceControlData(DeviceControlDataUpdate updateVO);

    void deleteDeviceControlData(Long id);

    List<DeviceControlDataResult> getDeviceControlDataList(DeviceControlDataQuery query);

}

package com.sana.devices.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.devices.entity.DeviceGroupEntity;
import com.sana.devices.entity.vo.query.DeviceGroupQuery;
import com.sana.devices.entity.vo.result.DeviceGroupListResult;
import com.sana.devices.entity.vo.result.DeviceGroupResult;
import com.sana.devices.entity.vo.save.DeviceGroupSave;
import com.sana.devices.entity.vo.save.SaveGroupDevice;
import com.sana.devices.entity.vo.update.DeviceGroupUpdate;
import com.sana.devices.entity.vo.update.GroupDevicedate;

import java.util.List;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
public interface DeviceGroupService extends BaseService<DeviceGroupEntity> {
    SanaPage<DeviceGroupResult> getDeviceGroupPage(DeviceGroupQuery query);

    void saveDeviceGroup(DeviceGroupSave saveVO);

    void updateDeviceGroup(DeviceGroupUpdate updateVo);

    void deleteDeviceGroup(List<Long> idList);

    void saveBindingDevice(SaveGroupDevice saveVO);

    void deleteBindingDevice(GroupDevicedate updateVo);

    List<DeviceGroupListResult> getDeviceGroupList();
}

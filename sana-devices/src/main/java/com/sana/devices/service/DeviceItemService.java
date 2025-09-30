package com.sana.devices.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.devices.entity.DeviceItemEntity;
import com.sana.devices.entity.vo.query.DeviceHistoryQuery;
import com.sana.devices.entity.vo.query.DeviceItemQuery;
import com.sana.devices.entity.vo.query.GroupDeviceItemQuery;
import com.sana.devices.entity.vo.result.DeviceItemResult;
import com.sana.devices.entity.vo.result.GroupDeviceItemResult;
import com.sana.devices.entity.vo.save.DeviceItemSave;
import com.sana.devices.entity.vo.update.DeviceItemUpdate;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
public interface DeviceItemService extends BaseService<DeviceItemEntity> {

    SanaPage<DeviceItemResult> getDeviceItemPage(DeviceItemQuery query);

    void saveDeviceItem(DeviceItemSave vo);

    void updateByUserId(DeviceItemUpdate vo);

    void deleteDeviceItem(List<Long> idList);

    SanaPage<GroupDeviceItemResult> groupDeviceItemPage(GroupDeviceItemQuery query);

    List<GroupDeviceItemResult> groupDeviceItemList(Long groupId, String deviceName);

    long getByDeviceType(Long id);

    SanaPage<Map<String, Object>> historyData(@Valid DeviceHistoryQuery query);

}

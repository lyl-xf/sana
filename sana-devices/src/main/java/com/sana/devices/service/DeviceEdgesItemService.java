package com.sana.devices.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.devices.entity.DeviceEdgesItemEntity;
import com.sana.devices.entity.vo.query.DeviceEdgesQuery;
import com.sana.devices.entity.vo.result.DeviceEdgesItemResult;
import com.sana.devices.entity.vo.result.EdgesItemDataResult;
import com.sana.devices.entity.vo.save.DeviceEdgesItemSave;
import com.sana.devices.entity.vo.update.DeviceEdgesItemUpdate;

import java.util.List;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
public interface DeviceEdgesItemService extends BaseService<DeviceEdgesItemEntity> {

    SanaPage<DeviceEdgesItemResult> getDeviceEdgesItemPage(DeviceEdgesQuery deviceEdgesQuery);
    DeviceEdgesItemEntity saveDeviceEdgesItem(DeviceEdgesItemSave saveVo);

    DeviceEdgesItemEntity updateDeviceEdgesItem(DeviceEdgesItemUpdate updateVo);

    DeviceEdgesItemResult getEdgesItemByProductTypeId(Long id);

    List<EdgesItemDataResult> getEdgesItemEdge(DeviceEdgesItemResult deviceEdgesItemResult);

    void delEdgesItem(String productTypeId);


}

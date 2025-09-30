package com.sana.devices.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.devices.convert.DeviceGroupConvert;
import com.sana.devices.dao.DeviceGroupDao;
import com.sana.devices.entity.DeviceGroupEntity;
import com.sana.devices.entity.vo.query.DeviceGroupQuery;
import com.sana.devices.entity.vo.result.DeviceGroupListResult;
import com.sana.devices.entity.vo.result.DeviceGroupResult;
import com.sana.devices.entity.vo.save.DeviceGroupSave;
import com.sana.devices.entity.vo.save.SaveGroupDevice;
import com.sana.devices.entity.vo.update.DeviceGroupUpdate;
import com.sana.devices.entity.vo.update.GroupDevicedate;
import com.sana.devices.service.DeviceGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Slf4j
@Service
public class DeviceGroupServiceImpl extends BaseServiceImpl<DeviceGroupDao, DeviceGroupEntity> implements DeviceGroupService {

    @Override
    public SanaPage<DeviceGroupResult> getDeviceGroupPage(DeviceGroupQuery query) {
        IPage<DeviceGroupResult> page = baseMapper.getDeviceGroupPage(getPage(query), query,true);
        return new SanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }

    @Override
    public void saveDeviceGroup(DeviceGroupSave saveVO) {
        DeviceGroupEntity entity = DeviceGroupConvert.INSTANCE.convert(saveVO);
        baseMapper.insert(entity);
    }

    @Override
    public void updateDeviceGroup(DeviceGroupUpdate updateVo) {
        DeviceGroupEntity entity = DeviceGroupConvert.INSTANCE.convert(updateVo);
        baseMapper.updateById(entity);
    }

    @Override
    public void deleteDeviceGroup(List<Long> idList) {
        // todo 先检查是否有绑定的设备，之后再进行删除
        baseMapper.deleteBatchIds(idList);

    }

    @Override
    public void saveBindingDevice(SaveGroupDevice saveVO) {
        baseMapper.saveBindingDevice(saveVO);
    }

    @Override
    public void deleteBindingDevice(GroupDevicedate updateVo) {
        baseMapper.deleteBindingDevice(updateVo);
    }

    @Override
    public List<DeviceGroupListResult> getDeviceGroupList() {
        return baseMapper.getDeviceGroupList();
    }
}

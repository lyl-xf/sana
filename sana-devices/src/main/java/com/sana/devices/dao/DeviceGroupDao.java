package com.sana.devices.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.dao.BaseDao;
import com.sana.devices.entity.DeviceGroupEntity;
import com.sana.devices.entity.vo.query.DeviceGroupQuery;
import com.sana.devices.entity.vo.result.DeviceGroupListResult;
import com.sana.devices.entity.vo.result.DeviceGroupResult;
import com.sana.devices.entity.vo.save.SaveGroupDevice;
import com.sana.devices.entity.vo.update.GroupDevicedate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Mapper
public interface DeviceGroupDao extends BaseDao<DeviceGroupEntity> {

    IPage<DeviceGroupResult> getDeviceGroupPage(@Param("page")IPage<DeviceGroupEntity> page, @Param("model") DeviceGroupQuery query, @Param("DataScopeIgnore") boolean b);

    void saveBindingDevice(@Param("saveVO") SaveGroupDevice saveVO);

    void deleteBindingDevice(@Param("updateVo") GroupDevicedate updateVo);

    List<DeviceGroupListResult> getDeviceGroupList();
}

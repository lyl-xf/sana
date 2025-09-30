package com.sana.devices.dao;

import com.sana.base.mybatis.dao.BaseDao;
import com.sana.devices.entity.DeviceProductModeEntity;
import com.sana.devices.entity.vo.result.DeviceProductModeResult;
import com.sana.devices.entity.vo.save.DeviceProductModeListSave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Mapper
public interface DeviceProductModeDao extends BaseDao<DeviceProductModeEntity> {

    List<DeviceProductModeResult> getProductModePage(Long id);

    void saveDeviceGroup(@Param("productTypeId") Long productTypeId, @Param("DeviceProductModeList")List<DeviceProductModeListSave> deviceProductModeList);

    void deleteDeviceGroup(@Param("productTypeId") Long productTypeId);
}

package com.sana.devices.dao;

import com.sana.base.mybatis.dao.BaseDao;
import com.sana.devices.entity.DeviceAbutmentEntity;
import com.sana.devices.entity.DeviceProductTypeEntity;
import com.sana.devices.entity.vo.query.DeviceProductTypeQuery;
import com.sana.devices.entity.vo.result.DeviceProductTypeResult;
import com.sana.devices.entity.vo.result.ProtocolResult;
import com.sana.devices.entity.vo.save.SaveDeviceAbutmentSave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Mapper
public interface DeviceProductTypeDao extends BaseDao<DeviceProductTypeEntity> {

    List<DeviceProductTypeResult> getList(@Param("params") DeviceProductTypeQuery query, @Param("DataScopeIgnore") boolean DataScopeIgnore);

    DeviceAbutmentEntity getProtocolsId(Long id);

    List<ProtocolResult> getDeviceProtocolsMode();

    void delDeviceProtocolsMode(@Param("productTypeId") Long productTypeId);

    void saveDeviceProtocolsMode(@Param("saveVO") SaveDeviceAbutmentSave saveVO);
}

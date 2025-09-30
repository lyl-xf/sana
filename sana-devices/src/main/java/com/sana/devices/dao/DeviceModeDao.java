package com.sana.devices.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.sana.base.mybatis.dao.BaseDao;
import com.sana.devices.entity.DeviceModeEntity;
import com.sana.devices.entity.vo.result.DeviceModeResult;
import com.sana.devices.entity.vo.result.DeviceProductModeResult;
import com.sana.devices.entity.vo.save.DeviceItemModeSave;
import com.sana.devices.entity.vo.update.DeviceModeUpdate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Mapper
public interface DeviceModeDao extends BaseDao<DeviceModeEntity> {

    void saveDeviceMode(@Param("productModeList") List<DeviceProductModeResult> productModeList, @Param("id") Long id);

    List<DeviceModeResult> getDeviceItemModeList(Long id);

    void deleteDeviceMode(@Param("deviceItemId")Long deviceItemId);

    void saveDeviceModes(@Param("saveVO") DeviceItemModeSave saveVO);


    @DS("tdengine")
    void deltTable(String sql);
    @DS("tdengine")
    void createTdTable(String sql);

    List<DeviceModeResult> getDeviceContoleList(Long id);

    void saveContoleModel(@Param("updataVO") DeviceModeUpdate updataVO);

    @DS("tdengine")
    String getTableExists(String sql);
}

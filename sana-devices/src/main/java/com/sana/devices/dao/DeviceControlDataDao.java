package com.sana.devices.dao;


import com.sana.base.mybatis.dao.BaseDao;
import com.sana.devices.entity.DeviceControlDataEntity;
import com.sana.devices.entity.vo.query.DeviceControlDataQuery;
import com.sana.devices.entity.vo.result.DeviceControlDataResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Mapper
public interface DeviceControlDataDao extends BaseDao<DeviceControlDataEntity> {


    List<DeviceControlDataResult> getDeviceControlDataList(@Param("query") DeviceControlDataQuery query);

}

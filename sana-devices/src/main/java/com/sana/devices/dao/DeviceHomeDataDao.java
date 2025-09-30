package com.sana.devices.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.sana.devices.entity.DevicesPinEntity;
import com.sana.devices.entity.LogCountEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Mapper
public interface DeviceHomeDataDao {

    DevicesPinEntity getDeviceStatusList();


    @DS("tdengine")
    long systemLog();

    @DS("tdengine")
    long deviceActionLog();

    @DS("tdengine")
    long deviceAlarmsLog();

    @DS("tdengine")
    long deviceAlarm();

    @DS("tdengine")
    long scenarioModeLog();

    @DS("tdengine")
    List<LogCountEntity> getLogCounts();
}

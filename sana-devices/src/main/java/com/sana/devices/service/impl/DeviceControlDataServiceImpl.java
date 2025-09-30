package com.sana.devices.service.impl;

import com.sana.base.cache.redis.CacheKeyBuilder;
import com.sana.base.cache.redis.RedisUtils;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.entity.DeviceControl;
import com.sana.devices.convert.DeviceControlDataConvert;
import com.sana.devices.dao.DeviceControlDataDao;
import com.sana.devices.entity.DeviceControlDataEntity;
import com.sana.devices.entity.vo.query.DeviceControlDataQuery;
import com.sana.devices.entity.vo.result.DeviceControlDataResult;
import com.sana.devices.entity.vo.save.DeviceControlDataSave;
import com.sana.devices.entity.vo.update.DeviceControlDataUpdate;
import com.sana.devices.service.DeviceControlDataService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Slf4j
@Service
public class DeviceControlDataServiceImpl extends BaseServiceImpl<DeviceControlDataDao, DeviceControlDataEntity> implements DeviceControlDataService {

    @Resource
    private RedisUtils redisCacheOps;

    @Override
    public DeviceControlDataEntity saveDeviceControl(DeviceControlDataSave saveVO) {
        //集合
        String key = CacheKeyBuilder.deviceControl(saveVO.getDeviceItemId().toString());
        DeviceControl deviceControl = new DeviceControl();
        //属性标志,处理指令的时候使用该字段进行下发
        deviceControl.setControlMode(saveVO.getControlMode());
        //功能名称，日志展示存储，更直观
        deviceControl.setControlName(saveVO.getControlName());
        //数据类型（int，String，float等）
        deviceControl.setControlDataType(saveVO.getControlDataType());
        //功能类型（1控制，2采集）
        deviceControl.setControlType(saveVO.getControlType());
        //设备id
        deviceControl.setDeviceItemId(saveVO.getDeviceItemId());
        //指令默认值，一般与ControlMode组成kv格式，如：{"key":"value"}
        deviceControl.setControlDefultValue(saveVO.getControlDefultValue());

        //设备功能存储到redis中，用于后期的指令处理
        Object data = redisCacheOps.get(key);
        //数据库实例化存储
        DeviceControlDataEntity entity = DeviceControlDataConvert.INSTANCE.convert(saveVO);
        if(saveVO.getId()!=null){
            //缓存处理
            if(data!=null){
                deviceControl.setId(saveVO.getId());
                boolean isNot = true;
                ArrayList<DeviceControl> RedisList = (ArrayList<DeviceControl>)data;
                //对比，是否存在与saveVO.getControlMode()想通的字段，如果存在，则替换，如果不存在则添加。
                for (DeviceControl deviceControl1 : RedisList){
                    if(deviceControl1.getId()==deviceControl.getId()){
                        RedisList.remove(deviceControl1);
                        RedisList.add(deviceControl);
                        isNot = false;
                    }
                }
                if(isNot){
                    RedisList.add(deviceControl);
                }
                redisCacheOps.delete(key);
                redisCacheOps.set(key,RedisList);
            }else {
                ArrayList<DeviceControl> DeviceControlList = new ArrayList<>();
                DeviceControlList.add(deviceControl);
                redisCacheOps.set(key,DeviceControlList);
            }
            baseMapper.updateById(entity);
            return entity;
        }else {

            baseMapper.insert(entity);
            if(data!=null){
                deviceControl.setId(entity.getId());
                ArrayList<DeviceControl> RedisList = (ArrayList<DeviceControl>)data;
                RedisList.add(deviceControl);
                redisCacheOps.delete(key);
                redisCacheOps.set(key,RedisList);
            }else {
                ArrayList<DeviceControl> DeviceControlList = new ArrayList<>();
                DeviceControlList.add(deviceControl);
                redisCacheOps.set(key,DeviceControlList);
            }

            return entity;
        }
    }

    @Override
    public void updateDeviceControlData(DeviceControlDataUpdate updateVO) {
        DeviceControlDataEntity entity = DeviceControlDataConvert.INSTANCE.convert(updateVO);
        baseMapper.updateById(entity);
    }

    @Override
    public void deleteDeviceControlData(Long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public List<DeviceControlDataResult> getDeviceControlDataList(DeviceControlDataQuery query) {
        List<DeviceControlDataResult> list = baseMapper.getDeviceControlDataList(query);
        return list;
    }

}

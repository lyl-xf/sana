package com.sana.devices.service.impl;


import com.sana.base.cache.redis.CacheKeyBuilder;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.entity.GeneralPrefix;
import com.sana.base.syshandle.enums.GeneralPrefixEnum;
import com.sana.devices.dao.DeviceModeDao;
import com.sana.devices.entity.DeviceModeEntity;
import com.sana.devices.entity.vo.result.ColumnResult;
import com.sana.devices.entity.vo.result.DeviceModeResult;
import com.sana.devices.entity.vo.result.DeviceProductModeResult;
import com.sana.devices.entity.vo.save.DeviceItemModeSave;
import com.sana.devices.entity.vo.save.DeviceModeListSave;
import com.sana.devices.entity.vo.update.DeviceModeUpdate;
import com.sana.devices.service.DeviceModeService;
import com.sana.base.cache.redis.RedisUtils;
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
public class DeviceModeServiceImpl extends BaseServiceImpl<DeviceModeDao, DeviceModeEntity> implements DeviceModeService {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private GeneralPrefix generalPrefix;
    @Override
    public void saveDeviceMode(List<DeviceProductModeResult> productModeList, Long id) {
        baseMapper.saveDeviceMode(productModeList,id);
    }

    @Override
    public void saveDeviceModes(DeviceItemModeSave saveVO) {
        if(saveVO.getDeviceModeListSave().size()>0){
            //删除
            baseMapper.deleteDeviceMode(saveVO.getDeviceItemId());
            baseMapper.saveDeviceModes(saveVO);
            //检索是否有这个表
            //删除td子表数据
            String tableExists = baseMapper.getTableExists("SHOW TABLES LIKE '"+generalPrefix.getTablePrefix()+saveVO.getDeviceItemId()+"';");
            if(tableExists!=null){
                    baseMapper.deltTable("DROP TABLE "+generalPrefix.getTablePrefix()+saveVO.getDeviceItemId()+";");
            }
            //创建td子表
            String sql = assembleTheSQL(saveVO.getDeviceModeListSave(),saveVO.getDeviceItemId());
            baseMapper.createTdTable(sql);
            String key = CacheKeyBuilder.deviceMode(generalPrefix.getTablePrefix()+saveVO.getDeviceItemId());
            Object data = redisUtils.get(key);
            //物模型加入缓存中
            ArrayList<String> objects = new ArrayList<>();
            for (DeviceModeListSave deviceModeListSave:saveVO.getDeviceModeListSave()) {
                objects.add(deviceModeListSave.getModeSigns());
            }
            if(data!=null){
                redisUtils.delete(key);
                redisUtils.set(key,objects);
            }else {
                redisUtils.set(key,objects);
            }
        }else {
            baseMapper.deleteDeviceMode(saveVO.getDeviceItemId());
        }
    }

    /**
     * 组装生成sql
     * @param deviceModeListSave
     * @return
     */
    private String assembleTheSQL(List<DeviceModeListSave> deviceModeListSave, long deviceId) {
        //组装普通表sql
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(generalPrefix.getTablePrefix());
        sb.append(deviceId);
        sb.append("(ts timestamp,");
        for (int i = 0; i < deviceModeListSave.size(); i++) {
            sb.append(deviceModeListSave.get(i).getModeSigns());
            sb.append(" ");
            sb.append(deviceModeListSave.get(i).getModeDataType());
            if (i < deviceModeListSave.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(");");
        return sb.toString();
    };


    @Override
    public List<DeviceModeResult> getDeviceItemModeList(Long id) {
        return baseMapper.getDeviceItemModeList(id);
    }

    @Override
    public List<DeviceModeResult> getDeviceContoleList(Long id) {
        return baseMapper.getDeviceContoleList(id);
    }

    @Override
    public void saveContoleModel(DeviceModeUpdate updataVO) {
        baseMapper.saveContoleModel(updataVO);
    }

    @Override
    public List<ColumnResult> getDeviceModeMap(Long deviceId) {
        List<DeviceModeResult> deviceModeList =baseMapper.getDeviceItemModeList(deviceId);
        if(deviceModeList!=null){
            List<ColumnResult> columnResults = new ArrayList<>();
            for (DeviceModeResult deviceModeResult : deviceModeList) {
                ColumnResult columnResult = new ColumnResult();
                columnResult.setLabel(deviceModeResult.getModeName());
                columnResult.setProp(deviceModeResult.getModeSigns());
                columnResults.add(columnResult);
            }
            ColumnResult columnResult = new ColumnResult();
            columnResult.setLabel("时间");
            columnResult.setProp("ts");
            columnResults.add(columnResult);
            return columnResults;
        }
        return List.of();
    }
}

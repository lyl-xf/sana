package com.sana.devices.service.impl;

import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.devices.dao.DeviceProductModeDao;
import com.sana.devices.entity.DeviceProductModeEntity;
import com.sana.devices.entity.vo.result.DeviceProductModeResult;
import com.sana.devices.entity.vo.save.DeviceProductModeSave;
import com.sana.devices.service.DeviceProductModeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Slf4j
@Service
public class DeviceProductModeServiceImpl extends BaseServiceImpl<DeviceProductModeDao, DeviceProductModeEntity> implements DeviceProductModeService {


    @Override
    public List<DeviceProductModeResult> getProductModePage(Long id) {
        return baseMapper.getProductModePage(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveDeviceGroup(DeviceProductModeSave saveVO) {

        if(saveVO.getDeviceProductModeListSave().size()>0){
            //todo 后续再优化吧
            //删除
            List<DeviceProductModeResult> deviceProductModeList = baseMapper.getProductModePage(saveVO.getProductTypeId());
            if(!deviceProductModeList.isEmpty()){
                baseMapper.deleteDeviceGroup(saveVO.getProductTypeId());
            }
            baseMapper.saveDeviceGroup(saveVO.getProductTypeId(),saveVO.getDeviceProductModeListSave());
        }else {
            baseMapper.deleteDeviceGroup(saveVO.getProductTypeId());
        }
    }
}

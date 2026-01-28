package com.sana.devices.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.exception.SanaException;
import com.sana.base.utils.TreeUtils;
import com.sana.devices.dao.DeviceProductTypeDao;
import com.sana.devices.entity.DeviceAbutmentEntity;
import com.sana.devices.entity.DeviceProductTypeEntity;
import com.sana.devices.entity.vo.query.DeviceProductTypeQuery;
import com.sana.devices.entity.vo.result.*;
import com.sana.devices.entity.vo.save.DeviceProductTypeSave;
import com.sana.devices.entity.vo.save.SaveDeviceAbutmentSave;
import com.sana.devices.entity.vo.update.DeviceProductTypeUpdate;
import com.sana.devices.service.DeviceEdgesItemService;
import com.sana.devices.service.DeviceItemService;
import com.sana.devices.service.DeviceProductTypeService;
import jakarta.annotation.Resource;
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
public class DeviceProductTypeServiceImpl extends BaseServiceImpl<DeviceProductTypeDao, DeviceProductTypeEntity> implements DeviceProductTypeService {

    @Resource
    private DeviceItemService deviceItemService;
    @Resource
    private DeviceEdgesItemService deviceEdgesItemService;


    @Override
    public List<DeviceProductTypeResult> getList(DeviceProductTypeQuery query) {
        //
        List<DeviceProductTypeResult> entityList = baseMapper.getList(query,true);
        return TreeUtils.build(entityList);
    }

    @Override
    public void saveDeviceProductType(DeviceProductTypeSave saveVO) {
        DeviceProductTypeEntity entity = new DeviceProductTypeEntity();
        BeanUtil.copyProperties(saveVO, entity);
        log.info("保存产品类型：{}", entity);
        baseMapper.insert(entity);
    }

    @Override
    public void updateDeviceProductType(DeviceProductTypeUpdate updateVO) {
        DeviceProductTypeEntity entity = new DeviceProductTypeEntity();
        BeanUtil.copyProperties(updateVO, entity);
        // 上级不能为自身
        if (entity.getId().equals(entity.getPid())) {
            throw new SanaException("上级类型不能为自身");
        }
        updateById(entity);
    }

    @Override
    public void deleteDeviceProductType(Long id) {

        //todo 模型绑定数据

        //是否被引用，如果被引用了，则需要删除对应的设备
        long deviceNum = deviceItemService.getByDeviceType(id);
        if(deviceNum>0){
            throw new SanaException("该产品已被绑定，请先删除所属的产品");
        }
        long orgCount = count(new QueryWrapper<DeviceProductTypeEntity>().eq("pid", id));
        if (orgCount > 0) {
            throw new SanaException("请先删除下级产品子项");
        }
        // 删除
        removeById(id);
    }

    @Override
    public DeviceAbutmentResult getDeviceProtocolsMode(Long id) {
        DeviceAbutmentResult deviceProtocolsMqttResult = new DeviceAbutmentResult();


        DeviceAbutmentEntity deviceAbutmentEntity = baseMapper.getProtocolsId(id);
        if(deviceAbutmentEntity!=null){
            deviceProtocolsMqttResult.setId(deviceAbutmentEntity.getAgreementId());
            deviceProtocolsMqttResult.setDeviceType(deviceAbutmentEntity.getDeviceType());
            deviceProtocolsMqttResult.setEdgeId(deviceAbutmentEntity.getEdgeId());
        }
        List<ProtocolResult> rotocolResult = baseMapper.getDeviceProtocolsMode();
        deviceProtocolsMqttResult.setRotocolResult(rotocolResult);

        DeviceEdgesItemResult deviceEdgesItemResult = deviceEdgesItemService.getEdgesItemByProductTypeId(id);
        deviceProtocolsMqttResult.setDeviceEdgesItemResult(deviceEdgesItemResult);
        List<EdgesItemDataResult> EdgesItemDataResult = deviceEdgesItemService.getEdgesItemEdge(deviceEdgesItemResult);
        deviceProtocolsMqttResult.setEdgesItemData(EdgesItemDataResult);
        // 边缘计算实例数据
        return deviceProtocolsMqttResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDeviceProtocolsMode(SaveDeviceAbutmentSave saveVO) {
        //1:直连设备,2:边缘计算设备,3:边缘计算子设备
        if(saveVO.getDeviceType()!=2){
            deviceEdgesItemService.delEdgesItem(saveVO.getProductTypeId().toString());
        }
        baseMapper.delDeviceProtocolsMode(saveVO.getProductTypeId());
        //deviceEdgesItemService.delEdgesItem(saveVO.getProductTypeId());
        baseMapper.saveDeviceProtocolsMode(saveVO);
    }
}

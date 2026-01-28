package com.sana.devices.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.devices.dao.DeviceEdgesItemDao;
import com.sana.devices.entity.DeviceEdgesItemEntity;
import com.sana.devices.entity.vo.query.DeviceEdgesQuery;
import com.sana.devices.entity.vo.result.DeviceEdgesItemResult;
import com.sana.devices.entity.vo.result.EdgesItemDataResult;
import com.sana.devices.entity.vo.save.DeviceEdgesItemSave;
import com.sana.devices.entity.vo.update.DeviceEdgesItemUpdate;
import com.sana.devices.service.DeviceEdgesItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Slf4j
@Service
public class DeviceEdgesItemServiceImpl  extends BaseServiceImpl<DeviceEdgesItemDao, DeviceEdgesItemEntity> implements DeviceEdgesItemService {


    @Override
    public SanaPage<DeviceEdgesItemResult> getDeviceEdgesItemPage(DeviceEdgesQuery deviceEdgesQuery) {
        IPage<DeviceEdgesItemResult> page = baseMapper.getDeviceEdgesItemPage(getPage(deviceEdgesQuery), deviceEdgesQuery,true);
        return new SanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }

    @Override
    public DeviceEdgesItemEntity saveDeviceEdgesItem(DeviceEdgesItemSave saveVo) {
        baseMapper.delEdgesItem(saveVo.getEdgeProductTypeId());
        DeviceEdgesItemEntity deviceEdgesItemEntity = new DeviceEdgesItemEntity();
        BeanUtil.copyProperties(saveVo, deviceEdgesItemEntity);
        baseMapper.insert(deviceEdgesItemEntity);
        return deviceEdgesItemEntity;
    }

    @Override
    public DeviceEdgesItemEntity updateDeviceEdgesItem(DeviceEdgesItemUpdate updateVo) {
        DeviceEdgesItemEntity deviceEdgesItemEntity = new DeviceEdgesItemEntity();
        BeanUtil.copyProperties(updateVo, deviceEdgesItemEntity);
        baseMapper.updateById(deviceEdgesItemEntity);
        return deviceEdgesItemEntity;
    }

    @Override
    public DeviceEdgesItemResult getEdgesItemByProductTypeId(Long id) {
        DeviceEdgesItemEntity deviceEdgesItemEntity = baseMapper.selectOne(new QueryWrapper<DeviceEdgesItemEntity>().eq("edge_product_type_id", id).eq("deleted",0));
        DeviceEdgesItemResult deviceEdgesItemResult = new DeviceEdgesItemResult();
        BeanUtil.copyProperties(deviceEdgesItemEntity, deviceEdgesItemResult);
        return deviceEdgesItemResult;
    }

    @Override
    public List<EdgesItemDataResult> getEdgesItemEdge(DeviceEdgesItemResult deviceEdgesItemResult) {
        return baseMapper.getEdgesItemEdge();
    }

    @Override
    public void delEdgesItem(String productTypeId) {
        baseMapper.delEdgesItem(productTypeId);
    }

}

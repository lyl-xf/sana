package com.sana.devices.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.cache.redis.CacheKeyBuilder;
import com.sana.base.cache.redis.RedisUtils;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.entity.GeneralPrefix;
import com.sana.base.syshandle.enums.GeneralPrefixEnum;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.devices.dao.DeviceItemDao;
import com.sana.devices.entity.DeviceItemEntity;
import com.sana.devices.entity.vo.query.DeviceHistoryQuery;
import com.sana.devices.entity.vo.query.DeviceItemQuery;
import com.sana.devices.entity.vo.query.GroupDeviceItemQuery;
import com.sana.devices.entity.vo.result.DeviceItemGetResult;
import com.sana.devices.entity.vo.result.DeviceItemResult;
import com.sana.devices.entity.vo.result.DeviceProductModeResult;
import com.sana.devices.entity.vo.result.GroupDeviceItemResult;
import com.sana.devices.entity.vo.save.DeviceItemSave;
import com.sana.devices.entity.vo.update.DeviceItemUpdate;
import com.sana.devices.service.DeviceItemService;
import com.sana.devices.service.DeviceModeService;
import com.sana.devices.service.DeviceProductModeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Slf4j
@Service
public class DeviceItemServiceImpl extends BaseServiceImpl<DeviceItemDao, DeviceItemEntity> implements DeviceItemService {
    @Resource
    private DeviceProductModeService deviceProductModeService;
    @Resource
    private DeviceModeService deviceModeService;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private GeneralPrefix generalPrefix;
    @Override
    public SanaPage<DeviceItemResult> getDeviceItemPage(DeviceItemQuery query) {
        String prefixs = generalPrefix.getDeviceTopicPrefix();
        IPage<DeviceItemGetResult> page = baseMapper.getDeviceItemPage(getPage(query),query,true);
        List<DeviceItemResult> deviceItemResults = page.getRecords().stream()
                .map(dto -> {

                    DeviceItemResult result = new DeviceItemResult();
                    // 复制基本属性...
                    result.setId(dto.getId());
                    result.setImgs(dto.getImgs());
                    result.setName(dto.getName());
                    result.setDeviceNumber(dto.getDeviceNumber());
                    result.setStatus(dto.getStatus());
                    result.setRemark(dto.getRemark());
                    result.setProductTypeId(dto.getProductTypeId());
                    result.setCreateTime(dto.getCreateTime());
                    result.setUpdateTime(dto.getUpdateTime());
                    result.setCreatorName(dto.getCreatorName());
                    result.setUpdaterName(dto.getUpdaterName());
                    result.setTopicName(prefixs+dto.getId());
                    result.setProductTypeShow(StringUtils.isNotBlank(dto.getProductTypeShow())
                            ? Arrays.stream(dto.getProductTypeShow().split(","))
                            .map(Long::valueOf)
                            .collect(Collectors.toList())
                            : Collections.emptyList());
                    return result;
                }).collect(Collectors.toList());

        return new SanaPage<>(deviceItemResults, page.getTotal(),page.getPages(),page.getSize());
    }


    @Override
    public SanaPage<Map<String, Object>> historyData(DeviceHistoryQuery query) {

        IPage<Map<String, Object>> page = baseMapper.getHistoryData(getPageMap(query),query,generalPrefix.getTablePrefix()+query.getDeviceId());
        //数据处理
        return new SanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());

    }



    @Override
    public void saveDeviceItem(DeviceItemSave vo) {

        DeviceItemEntity deviceItem = new DeviceItemEntity();
        //DeviceItemEntity deviceItem = DeviceItemConvert.INSTANCE.convert(vo);
        BeanUtils.copyProperties(vo,deviceItem);
        Long lastElement = vo.getProductTypeShow().stream()
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .orElse(null);
        deviceItem.setProductTypeId(lastElement);
        StringBuilder sb = new StringBuilder();
        for (Long number : vo.getProductTypeShow()) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(number);
        }
        deviceItem.setProductTypeShow(sb.toString());
        baseMapper.insert(deviceItem);
        //判断是否继承产品的物模型
        if(vo.getProductTypeMode()){
            //检查所继承的产品是否有物模型
            List<DeviceProductModeResult> productModeList =deviceProductModeService.getProductModePage(deviceItem.getProductTypeId());
            if(productModeList!=null){
                //继承，则维护到设备表中
                deviceModeService.saveDeviceMode(productModeList,deviceItem.getId());
                //生成设备子表，用于存储设备数据
                String sql = assembleTheSQL(productModeList,deviceItem.getId());
                //创建子表
                baseMapper.createTdTable(sql);
                String key = CacheKeyBuilder.deviceMode(generalPrefix.getTablePrefix() + deviceItem.getId());
                //创建物模型数据结构
                Object data = redisUtils.get(key);
                List<String> objects = new ArrayList<>();
                for (DeviceProductModeResult productModeResult:productModeList) {
                    objects.add(productModeResult.getModeSigns());
                }
                if(data!=null){
                    redisUtils.delete(key);
                    redisUtils.set(key,objects);
                }else {
                    redisUtils.set(key,objects);
                }
            }
        }
    }



    @Override
    public void updateByUserId(DeviceItemUpdate vo) {
        DeviceItemEntity deviceItem = new DeviceItemEntity();
        //DeviceItemEntity deviceItem = DeviceItemConvert.INSTANCE.convert(vo);
        BeanUtils.copyProperties(vo,deviceItem);
        Long lastElement = vo.getProductTypeShow().stream()
                .sorted(Comparator.reverseOrder())
                .findFirst()
                .orElse(null);
        deviceItem.setProductTypeId(lastElement);
        StringBuilder sb = new StringBuilder();
        for (Long number : vo.getProductTypeShow()) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(number);
        }
        deviceItem.setProductTypeShow(sb.toString());
        baseMapper.updateById(deviceItem);
    }

    @Override
    public void deleteDeviceItem(List<Long> idList) {
        removeByIds(idList);
    }

    @Override
    public SanaPage<GroupDeviceItemResult> groupDeviceItemPage(GroupDeviceItemQuery query) {
        IPage<GroupDeviceItemResult> page = baseMapper.groupDeviceItemPage(getPage(query),query,true);
        return new SanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }

    @Override
    public List<GroupDeviceItemResult> groupDeviceItemList(Long groupId,String deviceName) {
        return  baseMapper.groupDeviceItemList(groupId,deviceName);
    }

    @Override
    public long getByDeviceType(Long id) {
        //查询所属该产品的设备
        return baseMapper.getByDeviceType(id);
    }




    /**
     * 组装生成sql
     * @param productModeList
     * @return
     */
    private String assembleTheSQL(List<DeviceProductModeResult> productModeList,long deviceId) {
        //组装普通表sql
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE sb");
        sb.append(deviceId);
        sb.append("(ts timestamp,");
        for (int i = 0; i < productModeList.size(); i++) {
            sb.append(productModeList.get(i).getModeSigns());
            sb.append(" ");
            sb.append(productModeList.get(i).getModeDataType());
            if (i < productModeList.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(");");
        return sb.toString();
    };
}

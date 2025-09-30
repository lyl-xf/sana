package com.sana.devices.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.dao.BaseDao;
import com.sana.devices.entity.DeviceItemEntity;
import com.sana.devices.entity.vo.query.DeviceHistoryQuery;
import com.sana.devices.entity.vo.query.DeviceItemQuery;
import com.sana.devices.entity.vo.query.GroupDeviceItemQuery;
import com.sana.devices.entity.vo.result.DeviceItemGetResult;
import com.sana.devices.entity.vo.result.GroupDeviceItemResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Mapper
public interface DeviceItemDao extends BaseDao<DeviceItemEntity> {

    IPage<DeviceItemGetResult> getDeviceItemPage(@Param("page") IPage<DeviceItemEntity> page, @Param("query") DeviceItemQuery query, @Param("DataScopeIgnore") boolean DataScopeIgnore);

    void insertDeviceItemProduct(@Param("productId") List<Long> productTypeShow,@Param("itemid") Long id);

    void deleteDeviceItemProduct(@Param("itemid") Long id);

    IPage<GroupDeviceItemResult> groupDeviceItemPage(@Param("page") IPage<DeviceItemEntity> page, @Param("query") GroupDeviceItemQuery query, @Param("DataScopeIgnore") boolean b);

    List<GroupDeviceItemResult> groupDeviceItemList(@Param("groupId") Long groupId,@Param("deviceName")String deviceName);

    @DS("tdengine")
    void createTdTable(String sql);

    long getByDeviceType(@Param("id") Long id);

    @DS("tdengine")
    IPage<Map<String, Object>> getHistoryData(@Param("page") IPage<Map<String, Object>> page, @Param("query") DeviceHistoryQuery query, @Param("tableName") String tableName);
}

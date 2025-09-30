package com.sana.devices.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.dao.BaseDao;
import com.sana.devices.entity.DeviceEdgesItemEntity;
import com.sana.devices.entity.vo.query.DeviceEdgesQuery;
import com.sana.devices.entity.vo.result.DeviceEdgesItemResult;
import com.sana.devices.entity.vo.result.EdgesItemDataResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LON
 * @version 1.0
 * @data 2025/9/27
 */
@Mapper
public interface DeviceEdgesItemDao extends BaseDao<DeviceEdgesItemEntity> {

    List<EdgesItemDataResult> getEdgesItemEdge();

    void delEdgesItem(@Param("productTypeId") String productTypeId);

    IPage<DeviceEdgesItemResult> getDeviceEdgesItemPage(@Param("page")IPage<DeviceEdgesItemEntity> page, @Param("model") DeviceEdgesQuery deviceEdgesQuery, @Param("DataScopeIgnore") boolean b);
}

package com.sana.rules.dao;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.sana.rules.entity.DeviceControlEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;


/**
 * @author LON
 * @data 2025/9/27
 */
@Mapper
public interface DataAnalysisDao {
    @DS("tdengine")
    void save( @Param("sql")String sql);

    List<DeviceControlEntity> getDeviceControlList(@Param("longs") List<BigInteger> longs);
}

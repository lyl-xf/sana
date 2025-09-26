package com.sana.abutment.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author LON
 * @create 2025/9/21
 */
@Mapper
public interface DeviceStatusDao {

    void updataStatus(@Param("clientId") Long clientId, @Param("status") int status);
}
package com.sana.rules.service;

import com.sana.rules.entity.DeviceControlEntity;

import java.math.BigInteger;
import java.util.List;

/**
 * @author LON
 * @data 2025/9/27
 */

public interface DataAnalysisService {

    void saveTdEngine(String sql);

    List<DeviceControlEntity> getDeviceControlList(List<BigInteger> longs);
}

package com.sana.rules.service.impl;

import com.sana.rules.dao.DataAnalysisDao;
import com.sana.rules.entity.DeviceControlEntity;
import com.sana.rules.service.DataAnalysisService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author LON
 * @data 2025/9/27
 */
@Slf4j
@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {



    @Resource
    private DataAnalysisDao dataAnalysisDao;


    @Override
    public void saveTdEngine(String sql) {
        dataAnalysisDao.save(sql);
    }

    @Override
    public List<DeviceControlEntity> getDeviceControlList(List<BigInteger> longs) {
        return dataAnalysisDao.getDeviceControlList(longs);
    }
}

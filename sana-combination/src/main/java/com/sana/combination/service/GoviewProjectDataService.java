package com.sana.combination.service;

import com.sana.combination.entity.GoviewProjectDataEntity;

/**
 * @author LON
 * @create 2025/11/25 13:08
 */
public interface GoviewProjectDataService {


    GoviewProjectDataEntity getGoviewProjectDataEntity(Long id);

    void updateByIdEntity(GoviewProjectDataEntity goviewProjectDataEntity);

    void saveEntity(GoviewProjectDataEntity goviewProjectDataEntity);

    void deleteByProjectId(Long ids);
}

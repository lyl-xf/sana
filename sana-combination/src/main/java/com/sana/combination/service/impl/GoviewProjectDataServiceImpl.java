package com.sana.combination.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.combination.dao.GoviewProjectDataServiceDao;
import com.sana.combination.entity.GoviewProjectDataEntity;
import com.sana.combination.service.GoviewProjectDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author LON
 * @create 2025/11/25 13:13
 */
@Slf4j
@Service
public class GoviewProjectDataServiceImpl extends BaseServiceImpl<GoviewProjectDataServiceDao, GoviewProjectDataEntity>  implements GoviewProjectDataService {


    @Override
    public GoviewProjectDataEntity getGoviewProjectDataEntity(Long id) {
        GoviewProjectDataEntity goviewProjectDataEntity = baseMapper.selectOne(new QueryWrapper<GoviewProjectDataEntity>().eq("project_id", id));
        return goviewProjectDataEntity;
    }

    @Override
    public void updateByIdEntity(GoviewProjectDataEntity goviewProjectDataEntity) {
        baseMapper.updateById(goviewProjectDataEntity);
    }

    @Override
    public void saveEntity(GoviewProjectDataEntity goviewProjectDataEntity) {
        baseMapper.insert(goviewProjectDataEntity);
    }

    @Override
    public void deleteByProjectId(Long ids) {
        baseMapper.delete(new QueryWrapper<GoviewProjectDataEntity>().eq("project_id", ids));
    }
}

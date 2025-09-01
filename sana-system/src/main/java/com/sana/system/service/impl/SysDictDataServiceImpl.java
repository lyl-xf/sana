package com.sana.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.system.convert.SysDictDataConvert;
import com.sana.system.dao.SysDictDataDao;
import com.sana.system.entity.SysDictDataEntity;
import com.sana.system.entity.query.SysDictDataQuery;
import com.sana.system.entity.result.SysDictDataResult;
import com.sana.system.entity.save.SysDictDataSave;
import com.sana.system.entity.update.SysDictDataUpdate;
import com.sana.system.service.SysDictDataService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LON
 * @create 2025/9/1 14:45
 */
@Service
public class SysDictDataServiceImpl extends BaseServiceImpl<SysDictDataDao, SysDictDataEntity> implements SysDictDataService {


    @Override
    public SanaPage<SysDictDataResult> page(SysDictDataQuery query) {
        IPage<SysDictDataResult> page = baseMapper.getLists(getPage(query),query);
        return new SanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }

    @Override
    public void save(SysDictDataSave saveVO) {
        SysDictDataEntity entity = SysDictDataConvert.INSTANCE.convert(saveVO);

        baseMapper.insert(entity);
    }

    @Override
    public void update(SysDictDataUpdate updateVO) {
        SysDictDataEntity entity = SysDictDataConvert.INSTANCE.convert(updateVO);

        updateById(entity);
    }

    @Override
    public void delete(List<Long> idList) {
        removeByIds(idList);
    }
}
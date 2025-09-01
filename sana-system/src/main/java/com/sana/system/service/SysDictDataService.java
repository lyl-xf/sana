package com.sana.system.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.system.entity.SysDictDataEntity;
import com.sana.system.entity.query.SysDictDataQuery;
import com.sana.system.entity.result.SysDictDataResult;
import com.sana.system.entity.save.SysDictDataSave;
import com.sana.system.entity.update.SysDictDataUpdate;

import java.util.List;

/**
 * @author LON
 * @create 2025/8/30
 */
public interface SysDictDataService extends BaseService<SysDictDataEntity> {
    SanaPage<SysDictDataResult> page(SysDictDataQuery query);

    void save(SysDictDataSave saveVO);

    void update(SysDictDataUpdate updateVO);

    void delete(List<Long> idList);
}

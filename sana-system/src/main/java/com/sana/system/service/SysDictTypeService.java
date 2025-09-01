package com.sana.system.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.system.entity.SysDictTypeEntity;
import com.sana.system.entity.save.SysDictTypeSave;

import java.util.List;

/**
 * @author LON
 * @create 2025/8/30
 */
public interface SysDictTypeService extends BaseService<SysDictTypeEntity> {

    void save(SysDictTypeSave saveVO);

    void delete(List<Long> idList);

    List<SysDictTypeEntity> getList();

    void removeDictType(Long id);
}

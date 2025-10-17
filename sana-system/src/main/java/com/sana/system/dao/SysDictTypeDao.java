package com.sana.system.dao;

import com.sana.base.mybatis.dao.BaseDao;
import com.sana.system.entity.SysDictTypeEntity;
import com.sana.system.entity.result.SysDictTypeResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LON
 * @create 2025/8/30
 */
@Mapper
public interface SysDictTypeDao extends BaseDao<SysDictTypeEntity> {

    List<SysDictTypeResult> getList();
}

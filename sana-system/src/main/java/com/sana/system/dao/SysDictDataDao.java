package com.sana.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.dao.BaseDao;
import com.sana.system.entity.SysDictDataEntity;
import com.sana.system.entity.SysDictTypeEntity;
import com.sana.system.entity.query.SysDictDataQuery;
import com.sana.system.entity.result.SysDictDataResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author LON
 * @create 2025/8/30
 */
@Mapper
public interface SysDictDataDao extends BaseDao<SysDictDataEntity> {

    IPage<SysDictDataResult> getLists(@Param("page") IPage<SysDictDataEntity> page, @Param("query") SysDictDataQuery model);

}

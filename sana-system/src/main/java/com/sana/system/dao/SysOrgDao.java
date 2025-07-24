package com.sana.system.dao;


import com.sana.base.mybatis.dao.BaseDao;
import com.sana.system.entity.SysOrgEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysOrgDao extends BaseDao<SysOrgEntity> {

    Long getByUserId(Long userId);

    List<SysOrgEntity> getIdAndPidList();
}

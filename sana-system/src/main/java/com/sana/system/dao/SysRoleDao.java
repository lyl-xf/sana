package com.sana.system.dao;

import com.sana.base.mybatis.dao.BaseDao;
import com.sana.system.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author LON
 * @create 2025/7/24 16:14
 */
@Mapper
public interface SysRoleDao extends BaseDao<SysRoleEntity> {

    Integer getDataScopeByUserId(Long id);


}

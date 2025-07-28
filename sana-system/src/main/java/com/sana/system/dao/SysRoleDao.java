package com.sana.system.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.dao.BaseDao;
import com.sana.system.entity.SysRoleEntity;
import com.sana.system.entity.query.SysRoleQuery;
import com.sana.system.entity.result.SysRoleResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LON
 * @create 2025/7/24 16:14
 */
@Mapper
public interface SysRoleDao extends BaseDao<SysRoleEntity> {

    Integer getDataScopeByUserId(Long id);


    IPage<SysRoleResult> getPageRole(@Param("page") IPage<SysRoleEntity> page, @Param("model") SysRoleQuery model, @Param("DataScopeIgnore") boolean DataScopeIgnore);

    List<SysRoleEntity> getListRole(@Param("sysRoleQuery") SysRoleQuery sysRoleQuery,@Param("DataScopeIgnore") boolean DataScopeIgnore);

    void updateRole(@Param("roleid") Long roleid,@Param("grid") Integer grid);
}

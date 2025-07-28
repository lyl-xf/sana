package com.sana.system.dao;

import com.sana.base.mybatis.dao.BaseDao;
import com.sana.system.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {


    List<Long> getMenuIdList(@Param("roleId") Long id);

    void saveRoleMenu(@Param("roleid")Long roleid,@Param("menuIdList") List<Long> menuIdList);

    void deleteByRoleIdList(@Param("idList")List<Long> idList);
}

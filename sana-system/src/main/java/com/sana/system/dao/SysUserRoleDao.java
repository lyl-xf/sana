package com.sana.system.dao;

import com.sana.base.mybatis.dao.BaseDao;
import com.sana.system.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {

    List<Long> getRoleIdList(Long id);

    void saveUserRole(@Param("userId") Long userId,@Param("userRole") List<Long> userRole);

    void deleteByUserIdList(@Param("userId")Long userId);

    void deleteByUserIdLists(@Param("idList")List<Long> idList);

    void deleteByRoleIdList(@Param("roleIdList")List<Long> roleIdList);
}

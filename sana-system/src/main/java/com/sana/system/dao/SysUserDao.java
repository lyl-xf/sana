package com.sana.system.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.dao.BaseDao;
import com.sana.system.entity.SysUserEntity;
import com.sana.system.entity.result.SysUserCopyResult;
import com.sana.system.entity.query.SysUserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author LON
 * @create 2025/7/12
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {

    IPage<SysUserCopyResult> getLists(@Param("page") IPage<SysUserEntity> page, @Param("query") SysUserQuery query, @Param("DataScopeIgnore") boolean DataScopeIgnore);

    SysUserEntity getUserName(String username);
}

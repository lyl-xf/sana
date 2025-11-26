package com.sana.combination.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.dao.BaseDao;
import com.sana.combination.entity.GoviewEntity;
import com.sana.combination.entity.GoviewFileEntity;
import com.sana.combination.entity.query.GoviewProjectQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LON
 * @create 2025/11/25 13:15
 */
@Mapper
public interface GoViewServiceDao extends BaseDao<GoviewEntity> {
    int countData();
    List<GoviewEntity> getPage(@Param("page") Integer page, @Param("limit")  Integer limit);

    void uploadFile(GoviewFileEntity goviewFileEntity);

    IPage<GoviewEntity> goviewPage(@Param("page")IPage<GoviewEntity> page,@Param("model") GoviewProjectQuery query, @Param("DataScopeIgnore") boolean DataScopeIgnore);


    //List<GoviewEntity> getPage(SysUserQuery query, boolean DataScopeIgnore);
}

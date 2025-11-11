package com.sana.camera.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.dao.BaseDao;
import com.sana.camera.entity.CameraStreamProxyEntity;
import com.sana.camera.entity.query.CameraStreamProxyQuery;
import com.sana.camera.entity.result.CameraStreamProxyResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author LON
 * @create 2025/11/11 14:06
 */
@Mapper
public interface CameraStreamProxyDao extends BaseDao<CameraStreamProxyEntity> {


    IPage<CameraStreamProxyResult> getStreamProxyPage(@Param("page")IPage<CameraStreamProxyEntity> page,@Param("model") CameraStreamProxyQuery query,@Param("DataScopeIgnore") boolean b);
}

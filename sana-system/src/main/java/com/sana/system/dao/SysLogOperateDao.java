package com.sana.system.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.dao.BaseDao;
import com.sana.base.syshandle.operatelog.OptLogEntity;
import com.sana.system.entity.SysLogOperateEntity;
import com.sana.system.entity.query.SysLogOperateQuery;
import com.sana.system.entity.query.SysLogSysOperateQuery;
import com.sana.system.entity.result.JobInfoResult;
import com.sana.system.entity.result.SysLogOperateResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysLogOperateDao extends BaseDao<SysLogOperateEntity> {


    @DS("tdengine")
    void insertTd(@Param("sql")String sql);

    @DS("tdengine")
    IPage<SysLogOperateResult> getListsTd(@Param("page")IPage<SysLogOperateEntity> page,@Param("query") SysLogOperateQuery query);

    IPage<SysLogOperateResult> getLists(IPage<SysLogOperateEntity> page, SysLogOperateQuery query);

    @DS("tdengine")
    IPage<SysLogOperateResult> getSysListsTd(@Param("page")IPage<SysLogOperateEntity> page,@Param("query") SysLogSysOperateQuery query);

    IPage<SysLogOperateResult> getSysLists(IPage<SysLogOperateEntity> page, SysLogSysOperateQuery query);

    @DS("tdengine")
    IPage<SysLogOperateResult> getSysLogListsTd(IPage<SysLogOperateEntity> page, SysLogSysOperateQuery query);

    IPage<SysLogOperateResult> getSysLogLists(IPage<SysLogOperateEntity> page, SysLogSysOperateQuery query);

    @DS("tdengine")
    IPage<SysLogOperateResult> getdeviceLogListsTd(IPage<SysLogOperateEntity> page, SysLogSysOperateQuery query);

    IPage<SysLogOperateResult> getdeviceLogLists(IPage<SysLogOperateEntity> page, SysLogSysOperateQuery query);

    @DS("tdengine")
    IPage<SysLogOperateResult> getCommunLogListsTd(IPage<SysLogOperateEntity> page, SysLogSysOperateQuery query);

    IPage<SysLogOperateResult> getCommunLogLists(IPage<SysLogOperateEntity> page, SysLogSysOperateQuery query);

    JobInfoResult getJobData(String value);

    void saveBatch(@Param("optLogEntityList") List<OptLogEntity> optLogEntityList);



}

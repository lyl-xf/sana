package com.sana.system.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.system.entity.SysLogOperateEntity;
import com.sana.system.entity.query.SysLogOperateQuery;
import com.sana.system.entity.query.SysLogSysOperateQuery;
import com.sana.system.entity.result.JobInfoResult;
import com.sana.system.entity.result.SysLogOperateResult;

import java.util.List;

public interface SysLogOperateService extends BaseService<SysLogOperateEntity> {

    SanaPage<SysLogOperateResult> page(SysLogOperateQuery query);

    SanaPage<SysLogOperateResult> sysPage(SysLogSysOperateQuery query);

    List<JobInfoResult> getJobData();

    boolean updateLogJob(String jobName, String cron, String jobGroup, String triggerGroup, String value);

    boolean updateDeviceJob(String jobName, String cron, String jobGroup, String triggerGroup, String value);
}

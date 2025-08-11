package com.sana.system.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.system.entity.SysLogOperateEntity;
import com.sana.system.entity.query.SysLogOperateQuery;
import com.sana.system.entity.query.SysLogSysOperateQuery;
import com.sana.system.entity.result.SysLogOperateResult;

public interface SysLogOperateService extends BaseService<SysLogOperateEntity> {

    SanaPage<SysLogOperateResult> page(SysLogOperateQuery query);

    SanaPage<SysLogOperateResult> sysPage(SysLogSysOperateQuery query);
}

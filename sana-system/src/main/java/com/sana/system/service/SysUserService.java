package com.sana.system.service;


import com.sana.base.framework.mybatis.service.BaseService;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.system.entity.SysUserEntity;
import com.sana.system.entity.query.SysUserQuery;
import com.sana.system.entity.result.SysUserResult;
import com.sana.system.entity.update.SysUserPasswordUpdate;
import com.sana.system.entity.update.SysUserUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

/**
 * @author LON
 * @create 2025/7/12
 */
public interface SysUserService extends BaseService<SysUserEntity> {
    SanaPage<SysUserResult> page(SysUserQuery query);

    SysUserResult updateMySelf(SysUserUpdate sysUserUpdate);

    String updateMySelfPassword(SysUserPasswordUpdate sysUserPasswordUpdate);

    SysUserEntity getUserName(String username);
}

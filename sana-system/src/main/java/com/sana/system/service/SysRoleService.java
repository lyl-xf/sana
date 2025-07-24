package com.sana.system.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.system.entity.SysRoleEntity;
import com.sana.system.entity.SysUserEntity;


import java.util.List;

/**
 * @author LON
 * @create 2025/7/12
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

    List<Long> getDataScope(SysUserEntity user);
}

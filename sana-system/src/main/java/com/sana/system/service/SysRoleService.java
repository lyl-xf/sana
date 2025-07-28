package com.sana.system.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.system.entity.SysRoleEntity;
import com.sana.system.entity.SysUserEntity;
import com.sana.system.entity.query.SysRoleQuery;
import com.sana.system.entity.result.SysRoleResult;
import com.sana.system.entity.save.SysRoleSave;
import com.sana.system.entity.update.SysRoleDataScopeUpdate;
import com.sana.system.entity.update.SysRoleMenusUpdate;
import com.sana.system.entity.update.SysRoleUpdate;
import jakarta.validation.Valid;


import java.util.List;

/**
 * @author LON
 * @create 2025/7/12
 */
public interface SysRoleService extends BaseService<SysRoleEntity> {

    List<Long> getDataScope(SysUserEntity user);

    SanaPage<SysRoleResult> pageRole(@Valid SysRoleQuery query);

    List<SysRoleResult> getListRole(SysRoleQuery sysRoleQuery);

    void saveRole(@Valid SysRoleSave saveVO);

    void updateRole(@Valid SysRoleUpdate updateVo);

    void roleLinkMenus(@Valid SysRoleMenusUpdate sysRoleMenusUpdate);

    void dataScope(@Valid SysRoleDataScopeUpdate updateVO);

    void deleteRole(List<Long> idList);
}

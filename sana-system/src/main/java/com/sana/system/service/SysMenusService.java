package com.sana.system.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.base.syshandle.entity.MyUserDetails;
import com.sana.system.entity.SysMenusEntity;
import com.sana.system.entity.SysUserEntity;
import com.sana.system.entity.result.SysMenusNavResult;
import com.sana.system.entity.result.SysMenusResult;
import com.sana.system.entity.save.SysMenusSave;
import com.sana.system.entity.update.SysMenusUpdate;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Set;

/**
 * @author LON
 * @create 2025/7/12
 */
public interface SysMenusService extends BaseService<SysMenusEntity> {

    Set<String> getUserAuthority(SysUserEntity user);

    SysMenusNavResult getNavMenuList(MyUserDetails user);

    List<SysMenusResult> getMenuList(MyUserDetails user);

    SysMenusEntity saveMenu(@Valid SysMenusSave saveVO);

    void updateByData(@Valid SysMenusUpdate uodateVO);

    void delete(List<Long> ids);

    List<String> getRoleMenusList(MyUserDetails user, Long roleId);

    List<Long> getMenuIds(List<String> menus);
}

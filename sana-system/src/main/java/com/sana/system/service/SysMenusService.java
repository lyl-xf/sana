package com.sana.system.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.base.syshandle.entity.MyUserDetails;
import com.sana.system.entity.SysMenusEntity;
import com.sana.system.entity.SysUserEntity;
import com.sana.system.entity.result.SysMenusNavResult;

import java.util.Set;

/**
 * @author LON
 * @create 2025/7/12
 */
public interface SysMenusService extends BaseService<SysMenusEntity> {

    Set<String> getUserAuthority(SysUserEntity user);

    SysMenusNavResult getNavMenuList(MyUserDetails user);
}

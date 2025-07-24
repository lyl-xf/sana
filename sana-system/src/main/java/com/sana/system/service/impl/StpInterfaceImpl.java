package com.sana.system.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.util.StrUtil;
import com.sana.base.syshandle.enums.SuperAdminEnum;
import com.sana.system.entity.SysUserEntity;
import com.sana.system.service.SysMenusService;
import com.sana.system.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

/**
 * @author LON
 * @create 2025/7/12
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private SysMenusService sysMenusService;

    @Resource
    private SysUserService sysUserService;


    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        SysUserEntity UserEntity = sysUserService.getById((Serializable) loginId);
        Set<String> permsSet = sysMenusService.getUserAuthority(UserEntity);
        //现版本中，为了结合前端项目，不适用*来进行管理员的权限过滤。直接全查
        List<String> permList = permsSet.stream().toList();
        //将permsSet转为List<String>
        return permList;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     * 初版还是使用传统的角色校验方式，不在这里做处理
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return List.of();
    }
}

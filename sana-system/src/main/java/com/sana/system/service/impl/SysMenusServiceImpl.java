package com.sana.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.enums.SuperAdminEnum;
import com.sana.base.syshandle.enums.UserStatusEnum;
import com.sana.base.syshandle.exception.SanaException;
import com.sana.system.dao.SysMenusDao;
import com.sana.system.entity.SysMenusEntity;
import com.sana.system.entity.SysUserEntity;
import com.sana.system.entity.result.SysMenusNavResult;
import com.sana.system.entity.result.SysMenusResult;
import com.sana.system.service.SysMenusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author LON
 * @create 2025/7/23 16:30
 */
@Slf4j
@Service
public class SysMenusServiceImpl extends BaseServiceImpl<SysMenusDao, SysMenusEntity> implements SysMenusService {


    @Override
    public Set<String> getUserAuthority(SysUserEntity user) {
        List<String> authorityList;
        if (user.getSuperAdmin().equals(SuperAdminEnum.YES.getValue())) {
            authorityList = baseMapper.getAuthorityList();
        } else {
            authorityList = baseMapper.getUserAuthorityList(user.getId());
        }

        // 用户权限列表,数据过滤
        Set<String> permsSet = new HashSet<>();
        for (String authority : authorityList) {
            if (StrUtil.isBlank(authority)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(authority.trim().split(",")));
        }

        return permsSet;
    }

    @Override
    public SysMenusNavResult getNavMenuList() {
/*        StpUtil.getLoginIdAsLong();
        List<SysMenusResult> menuList = null;
        SysMenusNavResult sysNavResult = new SysMenusNavResult();
        // 系统管理员，拥有最高权限
        if (user.getSuperAdmin().equals(SuperAdminEnum.YES.getValue())) {
            menuList = baseMapper.getMenus();
        } else {
            //非管理员
            long userid = user.getId();
            menuList = baseMapper.getUserMenuList(userid);
        }
        //Tree处理
        sysNavResult.setMenu(TreeUtils.build(menuList));
        sysNavResult.setPermissions(user.getAuthoritySet());
        //手动添加仪表盘
        List<String> dashboardGrids = new ArrayList<>();
        dashboardGrids.add("welcome");
        dashboardGrids.add("ver");
        dashboardGrids.add("time");
        dashboardGrids.add("progress");
        dashboardGrids.add("echarts");
        dashboardGrids.add("about");

        sysNavResult.setDashboardGrid(dashboardGrids);
        return sysNavResult;*/
    }
}

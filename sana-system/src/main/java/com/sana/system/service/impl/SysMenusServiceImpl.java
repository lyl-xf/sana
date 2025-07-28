package com.sana.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.entity.MyUserDetails;
import com.sana.base.syshandle.enums.SuperAdminEnum;
import com.sana.base.syshandle.exception.SanaException;
import com.sana.base.utils.TreeUtils;
import com.sana.system.dao.SysMenusDao;
import com.sana.system.entity.SysMenusEntity;
import com.sana.system.entity.SysUserEntity;
import com.sana.system.entity.result.SysMenusNavResult;
import com.sana.system.entity.result.SysMenusResult;
import com.sana.system.entity.save.SysMenusSave;
import com.sana.system.entity.update.SysMenusUpdate;
import com.sana.system.service.SysMenusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author LON
 * @create 2025/7/23 16:30
 */
@Slf4j
@Service
public class SysMenusServiceImpl extends BaseServiceImpl<SysMenusDao, SysMenusEntity> implements SysMenusService {


    /**
     * 获取用户权限
     * @param user
     * @return
     */
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


    /**
     * 获取用户菜单
     * @param user
     * @return
     */
    @Override
    public SysMenusNavResult getNavMenuList(MyUserDetails user) {
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
        return sysNavResult;
    }

    @Override
    public List<SysMenusResult> getMenuList(MyUserDetails user) {
        List<SysMenusResult> menuList = null;
        // 系统管理员，拥有最高权限
        if (user.getSuperAdmin().equals(SuperAdminEnum.YES.getValue())) {
            menuList = baseMapper.getMenus();
        }else {
            menuList = baseMapper.getUserMenuList(user.getId());
        }
        return TreeUtils.build(menuList);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysMenusEntity saveMenu(SysMenusSave saveVO) {
        try {
            //增加菜单
            SysMenusEntity sysMenusEntity = new SysMenusEntity();
            BeanUtils.copyProperties(saveVO,sysMenusEntity);
            BeanUtils.copyProperties(saveVO.getMeta(),sysMenusEntity);
            // todo
            //拼接字段
            baseMapper.insert(sysMenusEntity);
            return sysMenusEntity;
        }catch (Exception e){
            log.info("新增菜单失败，请检查",e);
            throw new SanaException("新增菜单失败，请检查");
        }
    }

    @Override
    public void updateByData(SysMenusUpdate uodateVO) {
        try {
            //删除所有的api
            //增加、修改菜单
            SysMenusEntity sysMenusEntity = new SysMenusEntity();
            BeanUtils.copyProperties(uodateVO,sysMenusEntity);
            sysMenusEntity.setCode(uodateVO.getApiList().size() > 0 ? uodateVO.getApiList().get(0).getCode() : null);
            sysMenusEntity.setUrl(uodateVO.getApiList().size()> 0 ? uodateVO.getApiList().get(0).getUrl() : null);
            BeanUtils.copyProperties(uodateVO.getMeta(),sysMenusEntity);
            baseMapper.updateById(sysMenusEntity);
            // todo
        }catch (Exception e){
            log.info("修改菜单失败，请检查",e);
            throw  new SanaException("修改菜单失败，请检查");
        }
    }

    @Override
    public void delete(List<Long> ids) {
        baseMapper.deleteBatchIds(ids);
    }

    @Override
    public List<String> getRoleMenusList(MyUserDetails user, Long roleId) {
        return baseMapper.getRoleMenusList(roleId);
    }

    @Override
    public List<Long> getMenuIds(List<String> menus) {
        return baseMapper.getMenuIds(menus);
    }


}

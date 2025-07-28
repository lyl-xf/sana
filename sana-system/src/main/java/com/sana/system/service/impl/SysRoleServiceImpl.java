package com.sana.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.enums.DataScopeEnum;
import com.sana.base.syshandle.exception.SanaException;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.system.convert.SysRoleConvert;
import com.sana.system.dao.SysRoleDao;
import com.sana.system.entity.SysRoleEntity;
import com.sana.system.entity.SysUserEntity;
import com.sana.system.entity.query.SysRoleQuery;
import com.sana.system.entity.result.SysRoleResult;
import com.sana.system.entity.save.SysRoleSave;
import com.sana.system.entity.update.SysRoleDataScopeUpdate;
import com.sana.system.entity.update.SysRoleMenusUpdate;
import com.sana.system.entity.update.SysRoleUpdate;
import com.sana.system.service.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 本页面的todo，em....前端这块是每次登录将数据进行缓存然后走前端缓存。所以第一版暂时不做更新。设置好了权限退出、重新登录就好了
 * 第二版再优化一下前端，一旦更新了角色权限，将直接刷新缓存。
 * @author LON
 * @create 2025/7/12
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

    @Resource
    private SysOrgService sysOrgService;

    @Resource
    private SysMenusService sysMenusService;

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    /**
     * 根据用户详情获取数据权限范围。
     *
     * @param user 用户详细信息，包含用户ID和组织ID等。
     * @return 返回数据权限范围的ID列表。如果数据权限为全部，则返回null；如果是自定义范围，则返回自定义范围的ID列表。
     */
    public List<Long> getDataScope(SysUserEntity user) {

        /* 对照scui前端的列表进行处理
        <el-option label="全部可见" value="0"></el-option>
        <el-option label="所在部门及子级可见" value="1"></el-option>
        <el-option label="所在部门可见" value="2"></el-option>
        <el-option label="本人可见" value="3"></el-option>
        */

        // 以最大角色权限为主
        Integer dataScope = baseMapper.getDataScopeByUserId(user.getId());
        if (dataScope == null) {
            // 如果没有配置数据权限，则返回空列表
            return new ArrayList<>();
        }
        if (dataScope == (DataScopeEnum.ALL.getValue())) {
            // 如果数据权限为全部，则返回null
            return null;

        } else if (dataScope == (DataScopeEnum.ORGALL.getValue())) {
            // todo 备注，这里是根据用户信息进行进行查询的，也决定了这个的用户的上限。后期可以考虑走orgid路线
            // 如果数据权限为所在部门及子级可见，则获取本机构及子机构ID列表,并将组织下的用户信息加入到集合中
            List<Long> dataScopeList = sysOrgService.getOrgIdList(user.getId());
            return dataScopeList;


        } else if (dataScope == (DataScopeEnum.ORGONLY.getValue())) {
            // 如果数据权限仅为本机构，则只包含本机构ID
            List<Long> dataScopeList = sysOrgService.getOnlyOrgIdList(user.getId());
            return dataScopeList;

        }
        else if (dataScope == (DataScopeEnum.MYSELF.getValue())) {
            // 如果数据权限为自己可见
            List<Long> dataScopeList = new ArrayList<>();
            dataScopeList.add(user.getId());
            dataScopeList.add(-1L);
        }

        return new ArrayList<>();
    }





    @Override
    public SanaPage<SysRoleResult> pageRole(SysRoleQuery query) {
        IPage<SysRoleResult> page = baseMapper.getPageRole(getPage(query), query,true);
        return new SanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }

    @Override
    public List<SysRoleResult> getListRole(SysRoleQuery sysRoleQuery) {
        List<SysRoleEntity> entityList = baseMapper.getListRole(sysRoleQuery,true);
        return SysRoleConvert.INSTANCE.convertList(entityList);
    }

    @Override
    public void saveRole(SysRoleSave saveVO) {
        SysRoleEntity entity = SysRoleConvert.INSTANCE.convert(saveVO);
        baseMapper.insert(entity);
    }

    @Override
    public void updateRole(SysRoleUpdate updateVo) {
        SysRoleEntity entity = SysRoleConvert.INSTANCE.convert(updateVo);
        // 更新角色
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void roleLinkMenus(SysRoleMenusUpdate sysRoleMenusUpdate) {
        //
        try {
            Long roleid = sysRoleMenusUpdate.getRoleId();
            //维护角色菜单
            List<Long> menuIdList = sysMenusService.getMenuIds(sysRoleMenusUpdate.getMenus());
            sysRoleMenuService.deleteByRoleIdList(Collections.singletonList(roleid));
            sysRoleMenuService.saveRoleMenu(roleid, menuIdList);
            // todo 维护数据权限,角色信息的(角色绑定菜单、数据权限、首页模块权限)之后，为了兼容前端的功能，这里采用让其退出后再生效
            baseMapper.updateRole(roleid,sysRoleMenusUpdate.getGrid());
        }catch (Exception e){
            log.info("角色授权异常",e);
            throw new SanaException("角色授权异常：", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dataScope(SysRoleDataScopeUpdate updateVO) {
        try{
            SysRoleEntity entity = getById(updateVO.getId());
            entity.setDataScope(updateVO.getDataScope());
            // 更新角色
            updateById(entity);
            // todo 更新角色对应用户的缓存权限

        }catch (Exception e){
            log.info("修改数据权限错误",e);
            throw new SanaException("修改数据权限错误：", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(List<Long> idList) {
        try {
            // 删除角色
            removeByIds(idList);
            // 删除用户角色关系
            sysUserRoleService.deleteByRoleIdList(idList);
            // 删除角色菜单关系
            sysRoleMenuService.deleteByRoleIdList(idList);
            // todo 更新角色对应用户的缓存权限
            //查询出来这个角色下的用户的信息然后批量更新缓存中的数据
        }catch (Exception e){
            log.info("删除角色错误",e);
            throw new SanaException("删除角色错误：", e);
        }
    }

}

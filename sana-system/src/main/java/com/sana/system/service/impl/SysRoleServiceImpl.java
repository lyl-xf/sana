package com.sana.system.service.impl;

import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.enums.DataScopeEnum;
import com.sana.system.dao.SysRoleDao;
import com.sana.system.entity.SysRoleEntity;
import com.sana.system.entity.SysUserEntity;
import com.sana.system.service.SysOrgService;
import com.sana.system.service.SysRoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LON
 * @create 2025/7/12
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

    @Resource
    private SysOrgService sysOrgService;

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

}

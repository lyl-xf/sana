package com.sana.system.service.impl;

import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.system.dao.SysUserRoleDao;
import com.sana.system.entity.SysUserRoleEntity;
import com.sana.system.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LON
 * @version 1.0
 * @data 2025/8/3 16:35
 */
@Slf4j
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

    @Override
    public List<Long> getRoleIdList(Long id) {
        return null;
    }

    @Override
    public void deleteByUserIdList(List<Long> idList) {

    }

    @Override
    public void deleteByUserIdList(Long userId) {

    }

    @Override
    public void deleteByUserIdList(Long roleId, List<Long> userIdList) {

    }

    @Override
    public void deleteByRoleIdList(List<Long> roleIdList) {

        baseMapper.deleteByRoleIdList(roleIdList);

    }

    @Override
    public void saveUserList(Long roleId, List<Long> userIdList) {

    }

    @Override
    public void saveUserRole(Long userId, List<Long> userRole) {

    }
}

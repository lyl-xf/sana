package com.sana.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.usercache.UserCacheService;
import com.sana.system.dao.SysUserRoleDao;
import com.sana.system.entity.SysUserRoleEntity;
import com.sana.system.service.SysUserRoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LON
 * @version 1.0
 * @data 2025/8/3 16:35
 */
@Slf4j
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

    @Resource
    private UserCacheService userCacheService;

    @Override
    public List<Long> getRoleIdList(Long id) {
        return baseMapper.getRoleIdList(id);
    }

    @Override
    public void deleteByUserIdList(List<Long> idList) {
        baseMapper.deleteByUserIdLists(idList);
    }

    @Override
    public void deleteByUserIdList(Long userId) {
        baseMapper.deleteByUserIdList(userId);
    }

    @Override
    public void deleteByUserIdList(Long roleId, List<Long> userIdList) {
        LambdaQueryWrapper<SysUserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        remove(queryWrapper.eq(SysUserRoleEntity::getRoleId, roleId).in(SysUserRoleEntity::getUserId, userIdList));

        // 删除用户的缓存权限
        for (Long userId : userIdList){
            userCacheService.removeUserInfo(userId);
        }
    }

    @Override
    public void deleteByRoleIdList(List<Long> roleIdList) {

        baseMapper.deleteByRoleIdList(roleIdList);

    }

    @Override
    public void saveUserList(Long roleId, List<Long> userIdList) {
        List<SysUserRoleEntity> list = userIdList.stream().map(userId -> {
            SysUserRoleEntity entity = new SysUserRoleEntity();
            entity.setUserId(userId);
            entity.setRoleId(roleId);
            return entity;
        }).collect(Collectors.toList());
        // 批量新增
        saveBatch(list);
    }

    @Override
    public void saveUserRole(Long userId, List<Long> userRole) {
        baseMapper.saveUserRole(userId,userRole);
    }
}

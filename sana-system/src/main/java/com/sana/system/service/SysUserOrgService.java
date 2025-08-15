package com.sana.system.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.system.entity.SysUserOrgEntity;

import java.util.List;

/**
 * @author LON
 * @create 2025/8/16
 */
public interface SysUserOrgService extends BaseService<SysUserOrgEntity> {

    /**
     * 保存用户组织部门
     */
    void saveUserOrg(Long userId, List<Long> userOrg);

    /**
     * 删除用户部门
     */
    void deleteByUserIdList(List<Long> idList);

    /**
     * 删除用户部门
     */
    void deleteByUserIdList(Long userId);

}

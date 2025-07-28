package com.sana.system.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.system.entity.SysRoleMenuEntity;

import java.util.List;

public interface SysRoleMenuService extends BaseService<SysRoleMenuEntity> {

    /**
     * 根据角色ID，获取菜单ID列表
     * @param id
     * @return
     */
    List<Long> getMenuIdList(Long id);


    /**
     * 按角色 ID 列表删除
     * @param idList
     */
    void deleteByRoleIdList(List<Long> idList);


    void saveRoleMenu(Long roleid, List<Long> menuIdList);
}

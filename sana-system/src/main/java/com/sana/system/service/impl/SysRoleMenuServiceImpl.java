package com.sana.system.service.impl;

import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.system.dao.SysRoleMenuDao;
import com.sana.system.entity.SysRoleMenuEntity;
import com.sana.system.service.SysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther LON
 */
@Slf4j
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

    @Override
    public List<Long> getMenuIdList(Long id) {

        return baseMapper.getMenuIdList(id);
    }


    /**
     * 按角色 ID 列表删除
     * @param idList
     */
    @Override
    public void deleteByRoleIdList(List<Long> idList) {
        baseMapper.deleteByRoleIdList(idList);
    }


    @Override
    public void saveRoleMenu(Long roleid, List<Long> menuIdList) {

        baseMapper.saveRoleMenu(roleid, menuIdList);
    }


}

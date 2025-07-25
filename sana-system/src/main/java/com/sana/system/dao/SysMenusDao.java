package com.sana.system.dao;

import com.sana.base.mybatis.dao.BaseDao;
import com.sana.system.entity.SysMenusEntity;
import com.sana.system.entity.result.SysMenusResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LON
 * @create 2025/7/23 16:32
 */
@Mapper
public interface SysMenusDao extends BaseDao<SysMenusEntity> {

    List<String> getAuthorityList();

    List<String> getUserAuthorityList(Long id);

    List<SysMenusResult> getMenus();

    List<SysMenusResult> getUserMenuList(long userid);
}

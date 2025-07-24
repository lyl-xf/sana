package com.sana.system.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.system.entity.SysOrgEntity;

import java.util.List;

/**
 * @author LON
 * @create 2025/7/12
 */
public interface SysOrgService extends BaseService<SysOrgEntity> {


    List<Long> getOrgIdList(Long id);

    List<Long> getOnlyOrgIdList(Long id);
}

package com.sana.system.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.system.entity.SysOrgEntity;
import com.sana.system.entity.query.SysOrgQuery;
import com.sana.system.entity.result.SysOrgResult;
import com.sana.system.entity.save.SysOrgSave;
import com.sana.system.entity.update.SysOrgUpdate;
import jakarta.validation.Valid;

import java.util.List;

/**
 * @author LON
 * @create 2025/7/12
 */
public interface SysOrgService extends BaseService<SysOrgEntity> {


    List<Long> getOrgIdList(Long id);

    List<Long> getOnlyOrgIdList(Long id);

    List<SysOrgResult> getList(@Valid SysOrgQuery query);

    void saveOrg(@Valid SysOrgSave saveVO);

    void updateOrg(@Valid SysOrgUpdate updateVO);

    void deleteOrg(Long id);
}

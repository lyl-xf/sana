package com.sana.system.service.impl;

import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.system.dao.SysUserOrgDao;
import com.sana.system.entity.SysUserOrgEntity;
import com.sana.system.service.SysUserOrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LON
 * @create 2025/8/16
 */
@Slf4j
@Service
public class SysUserOrgServiceImpl extends BaseServiceImpl<SysUserOrgDao, SysUserOrgEntity> implements SysUserOrgService {
    @Override
    public void saveUserOrg(Long userId, List<Long> userOrg) {
        baseMapper.saveUserOrg(userId,userOrg);
    }

    @Override
    public void deleteByUserIdList(List<Long> idList) {
        baseMapper.deleteByUserIdLists(idList);
    }

    @Override
    public void deleteByUserIdList(Long userId) {
        baseMapper.deleteByUserIdList(userId);
    }
}

package com.sana.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.system.dao.SysOrgDao;
import com.sana.system.dao.SysUserDao;
import com.sana.system.entity.SysOrgEntity;
import com.sana.system.service.SysOrgService;
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
public class SysOrgServiceImpl extends BaseServiceImpl<SysOrgDao, SysOrgEntity> implements SysOrgService {

    @Resource
    private SysUserDao sysUserDao;

    @Override
    public List<Long> getOrgIdList(Long userId) {
        Long orgId = baseMapper.getByUserId(userId);
        // 所有机构的id、pid列表
        List<SysOrgEntity> orgList = baseMapper.getIdAndPidList();
        // 递归查询所有子机构ID列表
        List<Long> subIdList = new ArrayList<>();
        getTree(orgId, orgList, subIdList);
        // 本机构也添加进去
        subIdList.add(orgId);

        //查询出来所有的用户
        List<Long> userIds = sysUserDao.getUSerIds(subIdList);

        return userIds;
    }

    @Override
    public List<Long> getOnlyOrgIdList(Long userId) {
        Long orgId = baseMapper.getByUserId(userId);
        List<Long> userIds = sysUserDao.getOnlyOrgIdList(orgId);
        return userIds;
    }

    private void getTree(Long id, List<SysOrgEntity> orgList, List<Long> subIdList) {
        for (SysOrgEntity org : orgList) {
            if (ObjectUtil.equals(org.getPid(), id)) {
                getTree(org.getId(), orgList, subIdList);
                subIdList.add(org.getId());
            }
        }
    }
}

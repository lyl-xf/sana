package com.sana.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.exception.SanaException;
import com.sana.base.utils.TreeUtils;
import com.sana.system.dao.SysOrgDao;
import com.sana.system.dao.SysUserDao;
import com.sana.system.entity.SysOrgEntity;
import com.sana.system.entity.SysUserEntity;
import com.sana.system.entity.query.SysOrgQuery;
import com.sana.system.entity.result.SysOrgResult;
import com.sana.system.entity.save.SysOrgSave;
import com.sana.system.entity.update.SysOrgUpdate;
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
        // 查询所有子机构ID列表
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

    @Override
    public List<SysOrgResult> getList(SysOrgQuery query) {
        // 机构列表
        List<SysOrgResult> entityList = baseMapper.getList(query,true);
        return TreeUtils.build(entityList);
    }

    @Override
    public void saveOrg(SysOrgSave saveVO) {
        SysOrgEntity entity = new SysOrgEntity();
        BeanUtil.copyProperties(saveVO, entity);
        baseMapper.insert(entity);
    }

    @Override
    public void updateOrg(SysOrgUpdate updateVO) {
        SysOrgEntity entity = new SysOrgEntity();
        BeanUtil.copyProperties(updateVO, entity);
        // 上级机构不能为自身
        if (entity.getId().equals(entity.getPid())) {
            throw new SanaException("上级机构不能为自身");
        }
        // 上级机构不能为下级
        List<Long> subOrgList = getSubOrgIdList(entity.getId());
        if (subOrgList.contains(entity.getPid())) {
            throw new SanaException("上级机构不能为下级");
        }
        updateById(entity);
    }

    @Override
    public void deleteOrg(Long id) {
        // 判断是否有子机构
        long orgCount = count(new QueryWrapper<SysOrgEntity>().eq("pid", id));
        if (orgCount > 0) {
            throw new SanaException("请先删除子机构");
        }
        // todo 判断机构下面是否有用户
        long userCount = sysUserDao.selectCount(new QueryWrapper<SysUserEntity>().eq("org_id", id));
        if (userCount > 0) {
            throw new SanaException("机构下面有用户，不能删除");
        }
        // 删除
        removeById(id);
    }


    /**
     * 根据机构ID，获取子机构ID列表(包含本机构ID)
     * @param id   机构ID
     * @return
     */
    public List<Long> getSubOrgIdList(Long id) {
        // 所有机构的id、pid列表
        List<SysOrgEntity> orgList = baseMapper.getIdAndPidList();
        // 递归查询所有子机构ID列表
        List<Long> subIdList = new ArrayList<>();
        getTree(id, orgList, subIdList);
        // 本机构也添加进去
        subIdList.add(id);
        return subIdList;
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

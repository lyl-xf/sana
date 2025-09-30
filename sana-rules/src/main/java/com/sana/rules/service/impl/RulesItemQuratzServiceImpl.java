package com.sana.rules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.rules.dao.RulesItemQuratzDao;
import com.sana.rules.entity.RulesItemQuratzEntity;
import com.sana.rules.service.RulesItemQuratzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author LON
 * @data 2025/9/27
 */
@Slf4j
@Service
public class RulesItemQuratzServiceImpl extends BaseServiceImpl<RulesItemQuratzDao, RulesItemQuratzEntity> implements RulesItemQuratzService {
    @Override
    public void addRulesItemQuratz(RulesItemQuratzEntity rulesItemQuratzEntity) {
        baseMapper.insert(rulesItemQuratzEntity);
    }

    @Override
    public void deleteRulesItemQuratz(long rulesId) {
        baseMapper.deleteRulesItemQuratz(rulesId);
    }

    @Override
    public RulesItemQuratzEntity getRulesItemQuratz(long rulesId) {
        RulesItemQuratzEntity rulesItemQuratzEntity = baseMapper.selectOne(new QueryWrapper<RulesItemQuratzEntity>()
                .eq("rules_id", rulesId)
                .eq("deleted", 0)
        );
        return rulesItemQuratzEntity;
    }

    @Override
    public Long getRuleJobPushDevice(String jobGroup,String triggerGroup) {
        return baseMapper.getRuleJobPushDevice(jobGroup,triggerGroup);
    }
}

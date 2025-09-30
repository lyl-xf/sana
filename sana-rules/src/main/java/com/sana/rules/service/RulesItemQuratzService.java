package com.sana.rules.service;


import com.sana.base.mybatis.service.BaseService;
import com.sana.rules.entity.RulesItemQuratzEntity;


/**
 * @author LON
 * @data 2025/9/27
 */
public interface RulesItemQuratzService extends BaseService<RulesItemQuratzEntity> {

    void addRulesItemQuratz(RulesItemQuratzEntity rulesItemQuratzEntity);

    void deleteRulesItemQuratz(long rulesId);

    RulesItemQuratzEntity getRulesItemQuratz(long rulesId);

    Long getRuleJobPushDevice(String jobGroup,String triggerGroup);
}

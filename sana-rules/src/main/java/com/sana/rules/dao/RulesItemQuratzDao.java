package com.sana.rules.dao;

import com.sana.base.mybatis.dao.BaseDao;
import com.sana.rules.entity.RulesItemQuratzEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author LON
 * @data 2025/9/27
 */
@Mapper
public interface RulesItemQuratzDao extends BaseDao<RulesItemQuratzEntity> {

    void deleteRulesItemQuratz(long rulesId);

    Long getRuleJobPushDevice(@Param("jobGroup") String jobGroup,@Param("triggerGroup")  String triggerGroup);
}

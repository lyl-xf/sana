package com.sana.rules.service;


import com.sana.base.mybatis.service.BaseService;
import com.sana.base.syshandle.result.SanaResult;
import com.sana.rules.entity.RulesItemNodeEntity;
import com.sana.rules.entity.vo.query.RulesItemNodeQuery;
import com.sana.rules.entity.vo.result.RulesItemNodeResult;
import com.sana.rules.entity.vo.save.RulesItemNodeSave;

/**
 * @author LON
 * @data 2025/9/27
 */
public interface RulesItemNodeService extends BaseService<RulesItemNodeEntity> {


    SanaResult SaveAndUpdate(RulesItemNodeSave saveRulesItemNodeSaveVO);

    RulesItemNodeResult GetRulesItemDetail(RulesItemNodeQuery queryVO);
}

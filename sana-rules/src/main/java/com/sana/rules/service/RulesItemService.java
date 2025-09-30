package com.sana.rules.service;

import com.sana.base.mybatis.service.BaseService;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.rules.entity.RulesItemEntity;
import com.sana.rules.entity.vo.query.RulesItemPageQuery;
import com.sana.rules.entity.vo.result.RulesItemResult;
import com.sana.rules.entity.vo.save.RulesItemSave;
import com.sana.rules.entity.vo.update.RulesItemUpdate;

/**
 * @author LON
 * @data 2025/9/27
 */
public interface RulesItemService extends BaseService<RulesItemEntity> {

    //RulesItemResult getRules(RulesItemQuery queryVO);
    void saveRulesItem(RulesItemSave saveVO);

    void updateRulesItem(RulesItemUpdate updateVO);

    /**
     * 该方法暂时弃用
     * @param
     */
    //void updateAndSaveRulesItem(RulesItemUpdate updateVO);

    SanaPage<RulesItemResult> getRulesPage(RulesItemPageQuery query);


    void deleteRulesItem(Long id);

}

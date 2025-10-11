package com.sana.rules.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sana.base.mybatis.service.impl.BaseServiceImpl;
import com.sana.base.syshandle.page.SanaPage;
import com.sana.rules.convert.RulesItemConvert;
import com.sana.rules.dao.RulesItemDao;
import com.sana.rules.entity.RulesItemEntity;
import com.sana.rules.entity.vo.query.RulesItemPageQuery;
import com.sana.rules.entity.vo.result.RulesItemResult;
import com.sana.rules.entity.vo.save.RulesItemSave;
import com.sana.rules.entity.vo.update.RulesItemUpdate;
import com.sana.rules.service.RulesItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author LON
 * @data 2025/9/27
 */
@Slf4j
@Service
public class RulesItemServiceIpml extends BaseServiceImpl<RulesItemDao, RulesItemEntity> implements RulesItemService {

    @Value("${sana.aviator-path}")
    private String aviatorPath;


    @Override
    public SanaPage<RulesItemResult> getRulesPage(RulesItemPageQuery query) {
        IPage<RulesItemResult> page = baseMapper.getRulesPage(getPage(query), query,true);
        return new SanaPage<>(page.getRecords(), page.getTotal(),page.getPages(),page.getSize());
    }


/*    @Override
    public RulesItemResult getRules(RulesItemQuery queryVO) {

        RulesItemResult rulesItemResult = baseMapper.getRules(queryVO);

        return rulesItemResult;
    }*/

    @Override
    public void saveRulesItem(RulesItemSave saveVO) {
        RulesItemEntity rulesItemEntity = RulesItemConvert.INSTANCE.convert(saveVO);
        baseMapper.insert(rulesItemEntity);
    }

    @Override
    public void updateRulesItem(RulesItemUpdate updateVO) {
        RulesItemEntity rulesItemEntity = RulesItemConvert.INSTANCE.convert(updateVO);
        baseMapper.updateById(rulesItemEntity);
    }

    @Override
    public void deleteRulesItem(Long id) {
        baseMapper.deleteById(id);
        baseMapper.deleteRulesItemNode(id);
    }


}

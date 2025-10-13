package com.sana.rules.convert;

import com.sana.rules.entity.RulesItemNodeEntity;
import com.sana.rules.entity.vo.result.RulesItemNodeResult;
import com.sana.rules.entity.vo.save.RulesItemNodeSave;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author LON
 * @data 2025/9/27
 */
@Mapper
public interface RulesItemNodeConvert {
    RulesItemNodeConvert INSTANCE = Mappers.getMapper(RulesItemNodeConvert.class);


/*    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)*/
    RulesItemNodeEntity convert(RulesItemNodeSave saveRulesItemNodeSaveVO);

    RulesItemNodeResult convert(RulesItemNodeEntity rulesItemNodeEntity);
}

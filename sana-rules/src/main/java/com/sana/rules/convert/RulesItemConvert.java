package com.sana.rules.convert;

import com.sana.rules.entity.RulesItemEntity;
import com.sana.rules.entity.vo.save.RulesItemSave;
import com.sana.rules.entity.vo.update.RulesItemUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author LON
 * @data 2025/9/27
 */
@Mapper
public interface RulesItemConvert {
    RulesItemConvert INSTANCE = Mappers.getMapper(RulesItemConvert.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)
    RulesItemEntity convert(RulesItemSave saveVO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)
    RulesItemEntity convert(RulesItemUpdate updateVO);
}

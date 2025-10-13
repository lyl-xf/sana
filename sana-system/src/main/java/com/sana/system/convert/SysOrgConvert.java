package com.sana.system.convert;


import com.sana.system.entity.SysOrgEntity;
import com.sana.system.entity.result.SysOrgResult;
import com.sana.system.entity.save.SysOrgSave;
import com.sana.system.entity.update.SysOrgUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysOrgConvert {
    SysOrgConvert INSTANCE = Mappers.getMapper(SysOrgConvert.class);

/*    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)*/
    SysOrgEntity convert(SysOrgResult vo);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)
    SysOrgEntity convert(SysOrgSave saveVO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updater", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "updaterName", ignore = true)
    SysOrgEntity convert(SysOrgUpdate updateVO);

    @Mapping(target = "pid", ignore = true)
    @Mapping(target = "children", ignore = true)
    SysOrgResult convert(SysOrgEntity entity);


}
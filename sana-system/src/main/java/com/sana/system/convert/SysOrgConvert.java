package com.sana.system.convert;


import com.sana.system.entity.SysOrgEntity;
import com.sana.system.entity.result.SysOrgResult;
import com.sana.system.entity.save.SysOrgSave;
import com.sana.system.entity.update.SysOrgUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysOrgConvert {
    SysOrgConvert INSTANCE = Mappers.getMapper(SysOrgConvert.class);

    SysOrgEntity convert(SysOrgResult vo);

    SysOrgEntity convert(SysOrgSave saveVO);

    SysOrgEntity convert(SysOrgUpdate updateVO);

    SysOrgResult convert(SysOrgEntity entity);

    List<SysOrgResult> convertList(List<SysOrgEntity> list);

}